package controllers;

import play.mvc.*;
import play.data.*;
import static play.data.Form.*;
import play.data.validation.Constraints.*;

import views.html.*;

public class RepeatApp extends Controller {
    
    /**
     * Describes the hello form.
     */
    public static class Parrot {
        @Required public String name;
        @Required public String repeatText;
        @Required @Min(1) @Max(100) public Integer repeatTimes;
        public String color;
    } 
    
    // -- Actions
  
    /**
     * Home page
     */
    public static Result index() {
        return ok(
            parrotForm.render(form(Parrot.class))
        );
    }
  
    /**
     * Handles the form submission.
     */
    public static Result startParrot() {
        Form<Parrot> form = form(Parrot.class).bindFromRequest();
        if(form.hasErrors()) {
            return badRequest(parrotForm.render(form));
        } else {
            Parrot data = form.get();
            return ok(
                repeat.render(data.name, data.repeatText, data.repeatTimes, data.color)
            );
        }
    }
  
}
