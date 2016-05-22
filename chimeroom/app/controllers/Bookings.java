package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;
//import models.RoomsModel;
import models.BookingsModel;
import models.RoomsModel;
import java.util.*;
import java.text.*;

public class Bookings extends Controller {

    /**
     * Describes the booking form.
     */
    public static class BookingForm {
        public Long bid;
        public Long room_id;
        public String start_date;
        public String end_date;
        public Long emp_id;
        public String status;
        public String purpose;
        public Long num_participants;
        public String details;

    } 

    //GET to createbooking() -> render form
    public static Result createindex() {
        List<RoomsModel> r = RoomsModel.getAll();
       return ok(
           createbookingform.render(form(BookingForm.class), r )
        );
    }
  
    //POST to createbooking() -> call model
    public static Result create() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest();
        BookingForm f = null;
        List<RoomsModel> r = RoomsModel.getAll();
        if(form.hasErrors()) {
            return badRequest(createbookingform.render(form, r));
        } 
        f = form.get();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = format.parse(f.start_date);
            endDate = format.parse(f.end_date);
        } catch (ParseException e) {
            //to do 
        }

        BookingsModel b = BookingsModel.create(f.room_id, startDate, endDate, f.emp_id, f.status, f.num_participants, f.details);
        return ok(index.render("Your booking has been received"));
    }

    public static Result get() {
        List<BookingsModel> b = BookingsModel.getAll();
        return ok(getbookings.render(b));
    }

    //GET to modifybooking() -> render form
    public static Result modifyindex() {
        List<RoomsModel> r = RoomsModel.getAll();
        return ok(
            modifybookingform.render(form(BookingForm.class), r)
        );
    }
  
    //POST to modifybooking() -> call model
    public static Result modify() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest(); //TODO: Change this to modify form
        BookingForm f = null;
        if (form.hasErrors()) {
            List<RoomsModel> r = RoomsModel.getAll();
            return badRequest(modifybookingform.render(form, r)); //TODO: CHange this to modify form
        }
        f = form.get();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);
        Date startDate = new Date();
        Date endDate = new Date();
        try {
            startDate = format.parse(f.start_date);
            endDate = format.parse(f.end_date);
        } catch (ParseException e) {
            //to do 
        }

        BookingsModel b = BookingsModel.modify(f.bid, f.room_id, startDate, endDate, f.emp_id, f.purpose, f.num_participants, f.details, f.status);
        return ok(index.render("Modify Success ")); //TODO: Change this
    }

    //GET to deletebooking() -> render form
    public static Result deleteindex() {
        List<BookingsModel> b = BookingsModel.getAll();
        return ok(
            deletebookingform.render(form(BookingForm.class), b)
        );
    }
  
    //POST to deletebooking() -> call model
    public static Result delete() {
        Form<BookingForm> form = form(BookingForm.class).bindFromRequest(); //TODO: Change this to modify form
        BookingForm f = null;
        if (form.hasErrors()) {
            List<BookingsModel> b = BookingsModel.getAll();
            return badRequest(deletebookingform.render(form, b)); //TODO: CHange this to modify form
        }
        f = form.get();
        BookingsModel.delete(f.bid);
        return ok(index.render("Delete Success ")); //TODO: Change this
    }

}
