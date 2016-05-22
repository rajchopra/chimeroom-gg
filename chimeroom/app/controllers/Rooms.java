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
     * Describes the ConfRoom form.
     */
    public static class ConfRoom {
        public Long id;
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

    //GET to createroom() -> render form
    public static Result createindex() {
        return ok(
            createroomform.render(form(ConfRoom.class))
        );
    }

    //POST to createroom() -> call model
    public static Result create() {
        Form<ConfRoom> form = form(ConfRoom.class).bindFromRequest();
        ConfRoom d = null;
        if(form.hasErrors()) {
            return badRequest(createroomform.render(form));
        } 
        d = form.get();
        Facilities f = new Facilities(d.whiteboard, d.projector, d.internet, d.wifi, d.intercom, d.teleconferencing, d.videoconferencing);
        RoomsModel r = RoomsModel.createRoom(d.name, d.capacity, d.floor, d.accesslevel, f.toJsonString());
        return ok(index.render("Room successfully created."));
    }

    public static Result getRooms() {
        List<RoomsModel> r = RoomsModel.getAll();
        return ok(getrooms.render(r));
    }

    //GET to modifyroom() -> render form
    public static Result modifyindex() {
        return ok(
            modifyroomform.render(form(ConfRoom.class))
        );
    }

    //POST to modifyroom() -> call model
    public static Result modify() {
        Form<ConfRoom> form = form(ConfRoom.class).bindFromRequest();
        ConfRoom d = null;
        if(form.hasErrors()) {
            return badRequest(modifyroomform.render(form));
        } 
        d = form.get();
        Facilities f = new Facilities(d.whiteboard, d.projector, d.internet, d.wifi, d.intercom, d.teleconferencing, d.videoconferencing);
        RoomsModel r = RoomsModel.modifyRoom(d.id, d.name, d.capacity, d.floor, d.accesslevel, f.toJsonString());
        return ok(index.render("Modify Success")); //TODO: Change this
    }

    //GET to deleteroom() -> render form
    public static Result deleteindex() {
        return ok(
            deleteroomform.render(form(ConfRoom.class))
        );
    }

    //POST to deleteroom() -> call model
    public static Result delete() {
        Form<ConfRoom> form = form(ConfRoom.class).bindFromRequest();
        ConfRoom d = null;
        if(form.hasErrors()) {
            return badRequest(deleteroomform.render(form));
        } 
        d = form.get();
        RoomsModel.deleteRoom(d.id);
        return ok(index.render("Delete Success")); //TODO: Change this
    }

}
