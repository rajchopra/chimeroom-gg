package models;

import java.util.*;

import play.db.ebean.*;
import play.data.validation.Constraints.*;

import javax.persistence.*;

@Entity
public class Test extends Model {
    @Id
    public Long Id;

    public String Name;

    public static Finder<Long, Test> find = new Finder(Long.class, Test.class);
    
}
