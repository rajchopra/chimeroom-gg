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

def notify(name, email, status, roomid, roomname):
    sender = 'govind@exotel.in'
    receivers = [email]

    message = "From: Govind <govind@exotel.in>\n Hi " + name + ",\n You booking room id : " + str(roomid) + "\n RoomName : " + roomname + " \n -Thanks."
    smtpObj = smtplib.SMTP('localhost')
    smtpObj.sendmail(sender, receivers, message)

c = get_connection()
start = time.strftime("%Y-%m-%d %H:%M:%S")
end = (datetime.datetime.now() + datetime.timedelta(days=1)).strftime("%Y-%m-%d %H:%M:%S")
q = "SELECT * FROM `bookings` WHERE status = 'queued' AND start_date >= '" + start + "' and start_date <= '" + end + "' order by start_date desc"

c.execute(q)

print c.rowcount
for row in c.fetchall():
    print row
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
    notify(name, email, 'success', rId, rName)
    
    c.connection.commit()

c.close()




    
