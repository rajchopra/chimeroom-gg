package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;
import models.RoomsModel;
import java.util.*;

import helpers.Facilities;


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

    
    public static Result createindex() {
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
        Facilities f = new Facilities(data.whiteboard, data.projector, data.internet, data.wifi, data.intercom, data.teleconferencing, data.videoconferencing);
        RoomsModel r = RoomsModel.createRoom(data.name, data.capacity, data.floor, data.accesslevel, f.toJsonString());
    
        return ok(index.render("Room successfully created."));
    }

    public static Result getRooms() {
        List<RoomsModel> r = RoomsModel.getAll();
        return ok(getrooms.render(r));
    }

    public static Result home() {
        String txt = "I am homepage. Dont look like one, no option, you'll have to believe me.";
        return ok(index.render(txt));
    }

    public static Result modify() {
        Long id = new Long(1); //TODO: Get this. Rest of the original values should be passed as is.
        RoomsModel r = RoomsModel.modifyRoom(id, "RoomName", new Long(10), "GroundFloor", "active", null, "{Projector:true}");
        return ok(index.render("Yay!! Happy to create a new room for you.")); //TODO: Change this
    }

    public static Result delete() {
        Long id = new Long(1); //TODO: Get this from User
        RoomsModel.deleteRoom(id);
        return ok(index.render("Room successfully deleted.")); //TODO: Change this
    }

}
