package controllers;

import com.avaje.ebean.Ebean;
import models.Consumer;
import controllers.forms.Login;
import play.data.Form;
import play.db.ebean.Model;
import play.mvc.Result;
import services.ConsumerService;

public class Logins{

    private static ConsumerService consumerService = new ConsumerService();

    public static Result login() {
        return play.mvc.Controller.ok(views.html.login.render(Form.form(controllers.forms.Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = new Form<>(controllers.forms.Login.class);
        loginForm = Form.form(controllers.forms.Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.login.render(loginForm));
        } else {
            String email = loginForm.get().email;
            String password = loginForm.get().password;
            System.out.println(email);
            System.out.println(password);
            if (consumerService.authenticate(email, password)) {
                play.mvc.Controller.session().clear();
                play.mvc.Controller.session("email", email);
                System.out.println("login okey");
                return play.mvc.Controller.redirect(routes.Application.index());
            } else {
                System.out.println("login not okey");
                return play.mvc.Controller.badRequest(views.html.login.render(loginForm));
            }
        }
    }

    public static Result logout() {
        play.mvc.Controller.session().clear();
        play.mvc.Controller.flash("success", "You've been logged out");
        return play.mvc.Controller.redirect(routes.Logins.login());
    }
}
