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

}
