package controllers;

import play.data.Form;
import play.mvc.Result;
import viewmodels.LoginData;

/**
 * Created by Severin on 14.04.2015.
 */
public class MyDonationsController {
    public static Result myDonations() {
        return play.mvc.Controller.ok(views.html.myDonations.render());
    }
}
