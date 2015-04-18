package controllers;

import models.Donation;
import models.Project;
import models.User;
import play.data.Form;
import play.mvc.Result;
import play.mvc.Security;
import viewmodels.MyDonations.DonationData;
import viewmodels.MyDonations.MyDonationsData;
import viewmodels.MyDonations.ProjectDonationData;
import viewmodels.ProjectData;
import viewmodels.RegistrationData;

import java.util.List;

/**
 * Created by Severin on 14.04.2015.
 */
public class MyDonationsController {
    @Security.Authenticated(AuthenticationController.class)
    public static Result myDonations() {
        MyDonationsData data = getData();
        Form<MyDonationsData> form = Form.form(MyDonationsData.class).fill(data);

        return play.mvc.Controller.ok(views.html.myDonations.render(data));
    }

    private static MyDonationsData getData() {
        User user = ApplicationController.getCurrentUser();
        return getFormData(user);
    }
    protected static MyDonationsData getFormData(User user){
        List<Donation> donations = user.getDonations();
        MyDonationsData result = new MyDonationsData();

        for(Donation donation: donations){
            Project project = donation.getDonationGoal().getProject();
            String date = donation.getDate().toString();

            ProjectDonationData projectdata = result.getOrSetData(project, date, donation.getMessageToCollector());
            projectdata.donations.add(new DonationData(donation));
        }
        return result;
    }


}
