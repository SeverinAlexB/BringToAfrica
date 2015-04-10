package controllers;

import controllers.forms.Login;
import controllers.forms.Registration;
import models.Consumer;
import play.data.Form;
import play.mvc.Result;
import services.ConsumerService;
import org.mindrot.jbcrypt.BCrypt;

public class Registrations {


    public static Result registration() {
        return play.mvc.Controller.ok(views.html.ConsumerManagement.registration.render(Form.form(controllers.forms.Registration.class)));
    }

    public static Result register() {
        Form<Registration> form = Form.form(Registration.class).bindFromRequest();
        if (form.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.registration.render(form));
        } else {
            String hash = BCrypt.hashpw(form.get().password1,BCrypt.gensalt());

            Consumer c = new Consumer();
            c.setEmail(form.get().email.toLowerCase());
            c.setFirstName(form.get().firstname);
            c.setLastName(form.get().lastname);
            c.setPasswordHashedSalted(hash);
            ConsumerService.saveConsumer(c);
            return play.mvc.Controller.redirect(routes.Logins.login());
        }
    }
}
