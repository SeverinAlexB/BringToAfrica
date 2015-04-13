package controllers;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.data.Form;
import play.mvc.Result;
import services.ConsumerService;
import viewmodels.RegistrationData;

public class RegistrationController {


    public static Result registration() {
        return play.mvc.Controller.ok(views.html.ConsumerManagement.registration.render(Form.form(RegistrationData.class)));
    }

    public static Result register() {
        Form<RegistrationData> form = Form.form(RegistrationData.class).bindFromRequest();
        if (form.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.registration.render(form));
        } else {
            String hash = BCrypt.hashpw(form.get().password1,BCrypt.gensalt());

            User c = new User();
            c.setEmail(form.get().email.toLowerCase());
            c.setFirstName(form.get().firstname);
            c.setLastName(form.get().lastname);
            c.setPasswordHashedSalted(hash);
            c.save();

            ConsumerService.logIn(c.getEmail());

            return play.mvc.Controller.redirect(routes.ApplicationController.index());
        }
    }
}
