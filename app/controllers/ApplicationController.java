package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import services.ConsumerService;

public class ApplicationController extends Controller {

    public static Result index() {
        return ok(views.html.startpage.render());
    }
    public static Result errorBadRequest(String error) {
        return badRequest(views.html.error.render(400, error));
    }
    public static Result errorNotFound(String error) {
        return notFound(views.html.error.render(404, error));
    }


    public static User getCurrentUser() {
        String username = play.mvc.Controller.session().get("email");
        return ConsumerService.getConsumerByEmail(username);
    }
}
