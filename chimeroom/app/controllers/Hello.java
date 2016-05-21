package controllers;

import play.*;
import play.mvc.*;
import models.Test;
import views.html.*;
import java.util.*;

public class Hello extends Controller {

    public static String title = "Hey!!!";
    // private static void getConnection() {
    //     Database database = Databases.createFrom(
    //             "mydatabase",
    //             "com.mysql.jdbc.Driver",
    //             "jdbc:mysql://localhost/test",
    //             ImmutableMap.of(
    //                     "user", "test",
    //                     "password", "secret"
    //             )
    //      );
        
    //     Connection connection = database.getConnection();
        
    // }
    
    public static Result index() {
        List<Test> t = Test.find.where().eq("name", "Govind").findList();
	
        return ok(hello.render("Your new application is under construction...", t.get(0).Name));
    }

}
