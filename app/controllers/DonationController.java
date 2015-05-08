package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import play.mvc.Security;
import services.DonationTypeService;
import services.ProjectService;
import viewmodels.donation.CreateDonationData;
import viewmodels.donation.DonationData;
import viewmodels.donation.ProjectDonationData;

import java.sql.Date;
import java.util.ArrayList;

import java.util.List;
import java.util.stream.Collectors;

public class DonationController extends Controller {
    @Security.Authenticated(AuthenticationController.class)
    public static Result donate() {
        Form<CreateDonationData> form = Form.form(CreateDonationData.class).bindFromRequest();
        Project project = ProjectService.getProjectById(Long.parseLong(form.data().get("projectId")));

        if (form.hasErrors()) {
            return badRequest(views.html.project.donation.donate.render(project, form));
        } else {
            List<DonationGoal> goals = project.getDonationGoals();
            ArrayList<DonationData> donations = createDonations(form, goals);

            ProjectDonationData donation = new ProjectDonationData();
            donation.project = project;
            donation.donations = donations;
            return ok(views.html.project.donation.donateSuccess.render(donation));
        }
    }

    private static ArrayList<DonationData> createDonations(Form<CreateDonationData> form, List<DonationGoal> goals) {
        ArrayList<DonationData> donations = new ArrayList<>();

        String messageToCollector = form.get().remarks;
        User user = ApplicationController.getCurrentUser();

        for (int i = 0; i < form.get().amounts.size(); i++) {
            String typeName = form.get().donations.get(i);
            int amount = form.get().amounts.get(i);
            DonationGoal goal = getGoalByType(goals, typeName);

            Donation donation = createDonation(user, goal, amount, messageToCollector);

            donations.add(new DonationData(donation));
        }
        return donations;
    }

    private static Donation createDonation(User user, DonationGoal goal, int amount, String messageToCollector) {
        Donation donation = new Donation(user, goal);
        donation.setAmount(amount);
        donation.setDate(new Date((new java.util.Date()).getTime()));
        donation.setMessageToCollector(messageToCollector);
        donation.save();
        return donation;
    }

    private static DonationGoal getGoalByType(List<DonationGoal> goals, String typeName) {
        DonationType type = DonationTypeService.getDonationTypeByName(typeName);
        return goals.stream()
                .filter(g -> g.getType().getName().equals(type.getName()))
                .limit(2)
                .collect(Collectors.toList()).get(0);
    }
}
