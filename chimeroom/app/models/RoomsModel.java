package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
@Table(name="rooms")
public class RoomsModel extends Model {
    @Id
    public Long Id;

    public String Name;

    public Long Capacity;

    public String Floor;

    public String Status;

    public Long Accesslevel;

    public String Facilities;


    public static Finder<Long, RoomsModel> find = new Finder<Long, RoomsModel>(Long.class, RoomsModel.class);

    public static List<RoomsModel> getAll() {
        return find.where().eq("status", "active").findList();
    }

    public static RoomsModel createRoom(String Name, Long Capacity, String Floor, Long AccessLevel, String Facilities) {
	RoomsModel room = new RoomsModel();
        room.Name = Name;
        room.Capacity = Capacity;
        room.Floor = Floor;
        room.Status = "active";
        room.Accesslevel = AccessLevel;
        room.Facilities = Facilities;
        room.save();
        return room;
    }

    public static RoomsModel modifyRoom(Long Id, String Name, Long Capacity, String Floor, String Status, Long AccessLevel, String Facilities) {
	RoomsModel room = find.byId(Id);
        room.Name = Name;
        room.Capacity = Capacity;
        room.Floor = Floor;
        room.Status = Status;
        room.Accesslevel = AccessLevel;
        room.Facilities = Facilities;
        room.update();
        return room;
    }

    public static void deleteRoom(Long Id) {
        RoomsModel room = find.byId(Id);
	room.delete();
	return;
    }

}
