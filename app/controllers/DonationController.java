package controllers;

import models.*;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import services.DonationTypeService;
import services.ProjectService;
import viewmodels.donation.CreateDonationData;
import viewmodels.donation.DonationData;
import viewmodels.donation.ProjectDonationData;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DonationController extends Controller {
    public static Result donate() {
        Form<CreateDonationData> form = Form.form(CreateDonationData.class).bindFromRequest();
        Project project = ProjectService.getProjectById(form.get().projectId);

        if (form.hasErrors()) {
            return badRequest(views.html.project.donate.render(project, form));
        } else {
            List<DonationGoal> goals = project.getDonationGoals();
            ArrayList<DonationData> donations = createDonations(form, goals);

            ProjectDonationData donation = new ProjectDonationData();
            donation.project = project;
            donation.donations = donations;
            return ok(views.html.project.donateSuccess.render(donation));
        }
    }

    private static ArrayList<DonationData> createDonations(Form<CreateDonationData> form, List<DonationGoal> goals) {
        ArrayList<DonationData> donations = new ArrayList<>();

        for (int i = 0; i < form.get().amounts.size(); i++) {
            String typeName = form.get().donations.get(i);
            int amount = Integer.parseInt(form.get().amounts.get(i));

            User user = ApplicationController.getCurrentUser();
            DonationGoal goal = getGoalByType(goals, typeName);

            Donation donation = new Donation(user, goal);
            donation.setAmount(amount);
            donation.save();

            donations.add(new DonationData(donation));
        }
        return donations;
    }

    private static DonationGoal getGoalByType(List<DonationGoal> goals, String typeName) {
        DonationType type = DonationTypeService.getDonationTypeByName(typeName);
        return goals.stream()
                .filter(g -> g.getType().getName().equals(type.getName()))
                .limit(2)
                .collect(Collectors.toList()).get(0);
    }
}
