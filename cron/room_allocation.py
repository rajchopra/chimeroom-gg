#!/usr/bin/python
import MySQLdb
import datetime
import time
import smtplib

host = "localhost"
user = "chin"
password = "pass"
db = "chimeroom"
dbC = ""

def get_connection():
    dbC = MySQLdb.connect(host=host,    # your host, usually localhost
                         user=user,         # your usernam
                         passwd=password,  # your password
                         db=db)        # name of the data base
    dbC = dbC.cursor()
    return dbC

def notify_success(name, email, roomid, roomname):
    sender = 'govind@exotel.in'
    receivers = [email]

    message = "From: Govind <govind@exotel.in>Subject: Your Booking is approved\n\n Hi " + name + ",\n You booking room id : " + str(roomid) + "\n RoomName : " + roomname + " \n -Thanks."
    smtpObj = smtplib.SMTP('localhost')
    smtpObj.sendmail(sender, receivers, message)

def notify_failure(name, email, roomid, roomname):
    sender = 'govind@exotel.in'
    receivers = [email]

    message = "From: Govind <govind@exotel.in>\nSubject: Your Booking is cancelled\n\n Hi " + name + ",\n You booking is rejected because of room unavailability. Please schedule it on some other time. \n -Thanks."
    smtpObj = smtplib.SMTP('localhost')
    smtpObj.sendmail(sender, receivers, message)


def process():
    c = get_connection()
    start = time.strftime("%Y-%m-%d %H:%M:%S")
    end = (datetime.datetime.now() + datetime.timedelta(days=1)).strftime("%Y-%m-%d %H:%M:%S")
    q = "SELECT * FROM `bookings` WHERE status = 'queued' AND start_date >= '" + start + "' and start_date <= '" + end + "' order by start_date asc"

    c.execute(q)

    print "Total book that need to be processed is " + str(c.rowcount)
    for row in c.fetchall():
        #print row
        eId = row[4]
        bId = row[0]
        email = ""
        name = ""
        ePri = ""
        particip = row[7]
        c.execute("SELECT * FROM employees where id = " + str(eId))
        if (c.rowcount == 0):
            print "Booking Id:" + bId + " is not creating by valid user. rejecting it."
            c.execute("UPDATE bookings set status = 'rejected' where id = " + bId)
            continue
        else:
            res = c.fetchone()
            email = res[2]
            name = res[1]
            ePri = res[3]

        c = get_connection()
        c.execute("select * From rooms where status = 'active' and capacity >= " + str(particip) + " order by capacity asc")
        if (c.rowcount == 0):
            # if the time is < next 3 hours reject this and notify
            elapTime = datetime.datetime.now() + datetime.timedelta(hours=3)
            if (row[2] <= elapTime):
                print "We dont have available rooms. Lets tell them the same."
                print "Booking is confirmed for Name:" + name + "; Email: " + email + ";  BookingId : " + str(bId)
                notify_failure(name, email)
            else:
                print "No rooms available as of now. Moving on."
                continue

        rId = ""
        rName = ""
        for r in c.fetchall():
            # TODO :add more logic here.
            rId = r[0]
            rName = r[1]
            c.execute("update rooms set status = 'booked' where id = " + str(rId))

        c.execute("update bookings set room_id = " + str(rId)  + " , status = 'success' where id = "  + str(bId))
        print "Booking is confirmed for Name:" + name + "; Email: " + email + "; RoomId :" + str(rId) + "; RoomName:" + rName + "; BookingId : " + str(bId)
        notify_success(name, email, rId, rName)
        
        c.connection.commit()

    c.close()

def deallocate_rooms():
    c = get_connection()
    c.execute("select * from bookings where end_date <= now() and status = 'success'")
    for r in c.fetchall():
        rId = r[1]
        c.execute("update rooms set status = 'active' where status = 'booked' and id = " + str(rId))
    c.connection.commit()
    c.close()

while (1):
    deallocate_rooms()
    process()
    print "Sleeping for 30s before next run."
    time.sleep(30)
