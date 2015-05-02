package controllers;

import models.Project;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import viewmodels.donation.MyDonationsData;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Severin on 01.05.2015.
 */
public class MyProjectController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myProjects() {
        List<Project> data = ApplicationController.getCurrentUser().getMyProjects();
        return play.mvc.Controller.ok(views.html.user.myProjects.render(data));
    }
}
