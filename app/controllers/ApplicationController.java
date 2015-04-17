package controllers;

import models.User;
import play.mvc.Controller;
import play.mvc.Result;
import services.ConsumerService;

public class ApplicationController extends Controller {

    public static Result index() {
        return redirect(routes.ProjectController.getProjectWidgets(0));
    }

    public static User getCurrent() {
        String username = play.mvc.Controller.session().get("email");
        return ConsumerService.getConsumerByEmail(username);
    }
}
