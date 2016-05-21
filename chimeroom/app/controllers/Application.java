package controllers;

import play.*;
import play.mvc.*;

import models.Test;
import java.util.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getRooms() {
        List <Rooms> r = Rooms.getAll();
        return ok(hello.render("Find All active rooms: ", r.get(0).Name));
    }

    public static Result home() {
    	String txt = "I am homepage. Dont look like one, no option, you'll have to believe me.";
        return ok(homepage.render(txt));
    }

    public static Result create() {
        return ok(createroom.render("Yay!! Happy to create a new room for you.", "Hello"));
    }

    public static Result delete() {
        return ok(deleteroom.render("Oops!! We feel sorry that you want to delete the room.", "Hello"));
    }

}
