package controllers;

import models.Donation;
import models.User;
import play.mvc.Result;
import play.mvc.Security;
import viewmodels.MyDonations.MyDonationsData;

import java.util.List;

/**
 * Created by Severin on 14.04.2015.
 */
public class MyDonationsController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myDonations() {

        return play.mvc.Controller.ok(views.html.myDonations.render());
    }

    public static MyDonationsData getData() {
        User user = ApplicationController.getCurrentUser();
        List<Donation> donations = Donation.find.where().eq("user",user).findList();
        return null;
    }

}
