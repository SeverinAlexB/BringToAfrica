package controllers;

import models.Consumer;
import controllers.forms.Login;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Result;


public class Logins{
    public static Result login() {
        return play.mvc.Controller.ok(views.html.login.render(Form.form(controllers.forms.Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = new Form<>(controllers.forms.Login.class);
        if (loginForm.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.login.render(loginForm));
        } else {
            play.mvc.Controller.session().clear();
            play.mvc.Controller.session("email", loginForm.get().email);

            Model.Finder<String, Consumer> finder = new Model.Finder<>(String.class, Consumer.class);
            return play.mvc.Controller.redirect(routes.Application.index());
        }
    }

    public static Result logout() {
        play.mvc.Controller.session().clear();
        play.mvc.Controller.flash("success", "You've been logged out");
        return play.mvc.Controller.redirect(routes.Logins.login());
    }
}
