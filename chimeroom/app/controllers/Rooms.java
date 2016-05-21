package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;
import models.RoomsModel;
import java.util.*;


public class Rooms extends Controller {

    /**
     * Describes the hello form.
     */
    public static class ConfRoom {
        public String name;
        public Long capacity;
    	public String floor;
    	public String status;
    	public Long accesslevel;
	
        public String whiteboard;
    	public String projector;
    	public String internet;
    	public String wifi;
    	public String intercom;
    	public String teleconferencing;
    	public String videoconferencing;
    } 

    
    public static Result index() {
        return ok(
            createconfroomform.render(form(ConfRoom.class))
        );
    }
  
    public static Result create() {
	    Form<ConfRoom> form = form(ConfRoom.class).bindFromRequest();
        ConfRoom data = null;
        if(form.hasErrors()) {
            return badRequest(createconfroomform.render(form));
        } 
        data = form.get();
        RoomsModel r = RoomsModel.createRoom(data.name, data.capacity, data.floor, data.accesslevel, "");
	
        return ok(index.render("Your new application is ready."));
    }

    public static Result getRooms() {
        List<RoomsModel> r = RoomsModel.getAll();
        return ok(hello.render("Find All active roomsModel: ", r.get(0).Name));
    }

    public static Result home() {
        String txt = "I am homepage. Dont look like one, no option, you'll have to believe me.";
        return ok(homepage.render(txt));
    }

    public static Result modify() {
        Long id = new Long(1); //TODO: Get this. Rest of the original values should be passed as is.
        RoomsModel r = RoomsModel.modifyRoom(id, "RoomName", new Long(10), "GroundFloor", "active", null, "{Projector:true}");
        return ok(createroom.render("Yay!! Happy to create a new room for you.", "Hello")); //TODO: Change this
    }

    public static Result delete() {
        Long id = new Long(1); //TODO: Get this from User
	RoomsModel.deleteRoom(id);
        return ok(createroom.render("Yay!! Happy to create a new room for you.", "Hello")); //TODO: Change this
    }

}
