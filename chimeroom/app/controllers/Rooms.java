package controllers;

import play.*;
import play.mvc.*;

import models.Rooms;
import views.html.*;

public class Rooms extends Controller {

    /**
     * Describes the hello form.
     */
    public static class ConfRoom {
        @Required public String name;
        @Required public Long capacity;
	public Long floor;
	public String status;
	public Long accesslevel;
        public String facilities;
    } 

    
    public static Result create() {
	Form<ConfRoom> form = form(ConfRoom.class).bindFromRequest();
	ConfRoom data = null;
	if(form.hasErrors()) {
            return badRequest(createconfroomform.render(form));
        } 
        data = form.get();
        Rooms r = Rooms.createRoom(name, capacity, floor, accesslevel, facilities);
	
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

    public static Result modify() {
        id = new Long(1); //TODO: Get this. Rest of the original values should be passed as is.
        Rooms r = Rooms.modifyRoom(id, "RoomName", new Long(10), "GroundFloor", null, "{Projector:true}");
        return ok(createroom.render("Yay!! Happy to create a new room for you.", "Hello")); //TODO: Change this
    }

}
