package controllers;

import play.*;
import play.mvc.*;

import views.html.*;

public class Application extends Controller {

    public static Result index() {
        return ok(index.render("Your new application is ready."));
    }

    public static Result getRooms() {
        List <Rooms> r = Rooms.getAll();
        return ok(hello.render("Find All active rooms: ", r.get(0).Name));
    }
}
