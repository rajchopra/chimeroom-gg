package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;
//import models.RoomsModel;
import models.BookingsModel;
import java.util.*;


public class Bookings extends Controller {

    /**
     * Describes the booking form.
     */
    public static class BookingForm {
        public Long Id;
        public Long RoomId;
        public Date StartDate;
        public Date EndDate;
        public Long EmpId;
        public String Status;
        public String Purpose;
        public Long NumParticipants;
        public String AdditionalInfo;
    } 

    //GET to createbooking() -> render form
    public static Result createindex() {
        return ok(
            createbookingform.render(form(BookingForm.class))
        );
    }
  
    //POST to createbooking() -> call model
    public static Result create() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest();
        BookingForm f = null;
        if(form.hasErrors()) {
            return badRequest(createbookingform.render(form));
        } 
        f = form.get();
        BookingModel b = BookingModel.create(f.RoomId, f.StartDate, f.EndDate, f.EndDate, f.EmpId, f.Purpose, f.NumParticipants, f.AdditionalInfo);
        return ok(index.render("Your booking has been received"));
    }

    public static Result get() {
        List<BookingsModel> b = BookingsModel.getAll();
        return ok(hello.render("Find all bookings: ", r.get(0).Id));
    }

    //GET to modifybooking() -> render form
    public static Result modifyindex() {
        return ok(
            modifybookingform.render(form(BookingForm.class))
        );
    }
  
    //POST to modifybooking() -> call model
    public static Result modify() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest(); //TODO: Change this to modify form
        BookingForm f = null;
        if (form.hasErrors()) {
            return badRequest(modifybookingform.render(form)); //TODO: CHange this to modify form
        }
        f = form.get();
        BookingsModel b = BookingsModel.modify(f.Id, f.RoomId, f.StartDate, f.EndDate, f.EndDate, f.EmpId, f.Purpose, f.NumParticipants, f.AdditionalInfo, f.Status);
        return ok(hello.render("Modify Success")); //TODO: Change this
    }

    //GET to deletebooking() -> render form
    public static Result deleteindex() {
        return ok(
            deletebookingform.render(form(BookingForm.class))
        );
    }
  
    //POST to deletebooking() -> call model
    public static Result delete() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest(); //TODO: Change this to modify form
        BookingForm f = null;
        if (form.hasErrors()) {
            return badRequest(deletebookingform.render(form)); //TODO: CHange this to modify form
        }
        f = form.get();
        BookingsModel b = BookingsModel.delete(f.Id);
        return ok(hello.render("Delete Success")); //TODO: Change this
    }

}
