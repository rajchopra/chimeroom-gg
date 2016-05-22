package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name="bookings")
public class BookingsModel extends Model {
    @Id
    public Long Id;

    @Column(name="room_id")
    public Long RoomId;

    @Column(name="start_date")
    public Date StartDate;

    @Column(name="end_date")
    public Date EndDate;

    @Column(name="emp_id")
    public Long EmpId;

    public String Status;

    public String Purpose;

    @Column(name="num_participants")
    public Int NumParticipants;
    
    public String Details;

    public static Finder<Long, BookingsModel> find = new Finder<Long, BookingsModel>(Long.class, BookingsModel.class);

    public static BookingsModel get(Long Id) {
        return find.byId(Id);
    }

    public static List<BookingsModel> getAll(Date StartDate, Date EndDate, Long RoomId) {
        return find.where().eq("start_date", StartDate).eq("end_date", EndDate).eq("room_id", RoomId).findList();
    }

    public static BookingsModel create(Long RoomId, Date StartDate, Date EndDate, Long EmpId, String Purpose, Int NumParticipants, String Details) {
	BookingsModel b = new BookingsModel();
	b.RoomId = RoomId;
	b.StartDate = StartDate;
	b.EndDate = EndDate;
	b.EmpId = EmpId;
	b.Status = "queued";
	b.Purpose = Purpose;
	b.NumParticipants = NumParticipants;
	b.Details = Details;
        b.save();
        return b;
    }

    public static BookingsModel modify(Long Id, Long RoomId, Date StartDate, Date EndDate, Long EmpId, String Purpose, Int NumParticipants, String Details, String Status) {
	b = find.byId(Id);
	b.RoomId = RoomId;
	b.StartDate = StartDate;
	b.EndDate = EndDate;
	b.EmpId = EmpId;
	b.Status = Status;
	b.Purpose = Purpose;
	b.NumParticipants = NumParticipants;
	b.Details = Details;
        b.update();
        return b;
    }

    public static void delete(Long Id) {
        BookingsModel b = find.byId(Id);
	b.delete();
	return;
    }

}
