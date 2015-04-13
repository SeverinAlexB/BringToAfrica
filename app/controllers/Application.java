package controllers;

import models.Consumer;
import play.mvc.Controller;
import play.mvc.Result;
import services.ConsumerService;

public class Application extends Controller {

    public static Result index() {
        return redirect(routes.ProjectController.getProjects());
    }

    public static Consumer getCurrent() {
        String username = play.mvc.Controller.session().get("email");
        return ConsumerService.getConsumerByEmail(username);
    }
}
