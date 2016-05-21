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

    public String Location;

    public String Status;

    public Long Level;

    public String Facilities;

    public static Finder<Long, Rooms> find = new Finder<Long, Rooms>(Long.class, Rooms.class);

    public static List<Rooms> getAll() {
        return
	    find.where().eq("status", "active").findList();
    }
}
