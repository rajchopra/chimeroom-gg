package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Rooms extends Model {
    @Id
    public Long Id;

    public String Name;

    public Long Capacity;

    public String Floor;

    public String Status;

    public Long Accesslevel;

    public String Facilities;


    public static Finder<Long, Rooms> find = new Finder<Long, Rooms>(Long.class, Rooms.class);

    public static List<Rooms> getAll() {
        return find.where().eq("status", "active").findList();
    }

    public static Rooms createRoom(String Name, Long Capacity, String Floor, Long AccessLevel, String Facilities) {
	Rooms room = new Rooms();
        room.Name = Name;
        room.Capacity = Capacity;
        room.Floor = Floor;
        room.Status = "active";
        room.Accesslevel = AccessLevel;
        room.Facilities = Facilities;
        room.save();
        return room;
    }

    public status Rooms modifyRoom(Long Id, String Name, Long Capacity, String Floor, Long AccessLevel, String Facilities) {
	Rooms room = find.byId(Id);
        room.Name = Name;
        room.Capacity = Capacity;
        room.Floor = Floor;
        room.Status = "active";
        room.Accesslevel = AccessLevel;
        room.Facilities = Facilities;
        room.update();
        return room;
    }
}
