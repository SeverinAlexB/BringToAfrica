package services;

import models.DonationGoal;
import models.Project;

import java.util.List;

public class DonationGoalService {

    public static List<DonationGoal> getDonationGoalsOfProject(Project project){
        return DonationGoal.find.where().eq("project", project).findList();
    }

}
