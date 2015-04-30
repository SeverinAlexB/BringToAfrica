package services;

import models.DonationGoal;
import models.Project;

import java.util.List;

public class DonationGoalService {

    public static List<DonationGoal> getDonationGoalsOfProject(Project project){
        return DonationGoal.find.where().eq("project", project).findList();
    }

    public static int getState(DonationGoal donationGoal){
        return (donationGoal.getDonations().size() / donationGoal.getAmount()) * 100;
    }
}
