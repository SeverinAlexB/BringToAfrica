package controllers;

import controllers.forms.Login;
import controllers.forms.Registration;
import play.data.Form;
import play.mvc.Result;
import services.ConsumerService;

public class Registrations {


    public static Result registration() {
        return play.mvc.Controller.ok(views.html.ConsumerManagement.registration.render(Form.form(controllers.forms.Registration.class)));
        // return play.mvc.Controller.ok(views.html.ConsumerManagement.Registration.render(Form.form(Login.class)));
    }

    public static Result register() {
        Form<Registration> form = Form.form(Registration.class).bindFromRequest();
        if (form.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.registration.render(form));
        } else {
            String email = form.get().email;
            String password = form.get().password1;
            if (ConsumerService.isValid(email, password)) {
                play.mvc.Controller.session().clear();
                play.mvc.Controller.session("email", email);
                return play.mvc.Controller.redirect(routes.Application.index());
            } else {
                return play.mvc.Controller.badRequest(views.html.ConsumerManagement.registration.render(form));
            }
        }
    }

    public static Result logout() {
        play.mvc.Controller.session().clear();
        play.mvc.Controller.flash("success", "You've been logged out");
        return play.mvc.Controller.redirect(routes.Application.index());
    }
}
