package controllers;

import controllers.forms.Login;
import play.data.Form;
import play.mvc.Result;
import services.ConsumerService;

public class Registration {


    public static Result registration() {
        return play.mvc.Controller.ok(views.html.ConsumerManagement.registration.render());
        // return play.mvc.Controller.ok(views.html.ConsumerManagement.Registration.render(Form.form(Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.login.render(loginForm));
        } else {
            String email = loginForm.get().email;
            String password = loginForm.get().password;
            if (ConsumerService.isValid(email, password)) {
                play.mvc.Controller.session().clear();
                play.mvc.Controller.session("email", email);
                return play.mvc.Controller.redirect(routes.Application.index());
            } else {
                return play.mvc.Controller.badRequest(views.html.ConsumerManagement.login.render(loginForm));
            }
        }
    }

    public static Result logout() {
        play.mvc.Controller.session().clear();
        play.mvc.Controller.flash("success", "You've been logged out");
        return play.mvc.Controller.redirect(routes.Application.index());
    }
}
