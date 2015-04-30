package services;

import models.Donation;
import models.DonationGoal;
import models.Project;

import java.util.List;

public class DonationGoalService {

    public static List<DonationGoal> getDonationGoalsOfProject(Project project){
        return DonationGoal.find.where().eq("project", project).findList();
    }

    public static int getState(DonationGoal donationGoal){
        double donations = 0.0;
        for(Donation donation : donationGoal.getDonations()) {
            donations += donation.getAmount();
        }
        double state = donations / (double)donationGoal.getAmount();
        if(state > 1) state = 1;
        return (int)(state * 100);
    }
}
