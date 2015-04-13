package controllers;


import controllers.forms.Login;
import play.data.Form;
import play.mvc.Result;
import services.ConsumerService;

public class LoginController {


    public static Result login() {
        return play.mvc.Controller.ok(views.html.ConsumerManagement.login.render(Form.form(controllers.forms.Login.class)));
    }

    public static Result authenticate() {
        Form<Login> loginForm = Form.form(controllers.forms.Login.class).bindFromRequest();
        if (loginForm.hasErrors()) {
            return play.mvc.Controller.badRequest(views.html.ConsumerManagement.login.render(loginForm));
        } else {
            String email = loginForm.get().email;
            String password = loginForm.get().password;
            if (ConsumerService.isValid(email, password)) {
                ConsumerService.logIn(email);
                return play.mvc.Controller.redirect(routes.Application.index());
            } else {
                return play.mvc.Controller.badRequest(views.html.ConsumerManagement.login.render(loginForm));
            }
        }
    }

    public static Result logout() {
        play.mvc.Controller.session().clear();
        play.mvc.Controller.flash("success", "Du wurdest erfolgreich ausgeloggt!");
        return play.mvc.Controller.redirect(routes.Application.index());
    }
}
