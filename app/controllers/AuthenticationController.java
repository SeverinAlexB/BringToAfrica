package controllers;

import models.User;
import play.mvc.*;
import play.mvc.Http.*;
import services.ConsumerService;


public class AuthenticationController extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        User user = ConsumerService.getConsumerByEmail(ctx.session().get("email"));
        if (user != null) {
            return ctx.session().get("email");
        } else {
            play.mvc.Controller.session().clear();
            play.mvc.Controller.flash("bad", "Session wurde gecleart!");
            return null;
        }

    }
    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.LoginController.login());
    }
}
