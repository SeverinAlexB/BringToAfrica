package controllers;

import models.Project;
import play.mvc.Result;
import play.mvc.Security;
import java.util.List;


public class MyProjectController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myProjects() {
        List<Project> data = ApplicationController.getCurrentUser().getMyProjects();
        return play.mvc.Controller.ok(views.html.user.myProjects.render(data));
    }
}
