package controllers;

import models.Consumer;
import play.mvc.*;
import play.mvc.Http.*;
import services.ConsumerService;


public class Secured extends Security.Authenticator {

    @Override
    public String getUsername(Context ctx) {
        Consumer consumer = ConsumerService.getConsumerByEmail(ctx.session().get("email"));
        if(consumer != null) {
            return ctx.session().get("email");
        }else{
            play.mvc.Controller.session().clear();
            play.mvc.Controller.flash("bad", "Session wurde gecleart!");
            return null;
        }

    }

    @Override
    public Result onUnauthorized(Context ctx) {
        return redirect(routes.Logins.login());
    }
}