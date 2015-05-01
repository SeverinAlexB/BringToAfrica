package viewmodels;

import models.Donation;
import models.DonationGoal;
import models.Project;
import models.User;
import services.DonationGoalService;
import services.ProjectService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class ProjectDetail {
    private int state;
    private Project project;

    public ProjectDetail(models.Project project) {
        this.state = ProjectService.getStateOfProjectInPercent(project);
        this.project = project;
    }

    public int getState() {
        return state;
    }

    public Project getProject() {
        return project;
    }

    public int getStateOfDonationGoalInPercent(DonationGoal donationGoal) {
        return DonationGoalService.getStateInPercent(donationGoal);
    }

    public int getStateOfDonationGoal(DonationGoal donationGoal) {
        return DonationGoalService.getState(donationGoal);
    }

    public Set<User> getDonators() {
        return ProjectService.getDonators(this.project);
    }

    public List<Donation> getDonationsForUser(User user) {
        List<Donation> donations = new ArrayList<>();
        for(DonationGoal donationGoal : project.getDonationGoals()) {
            for(Donation donation : donationGoal.getDonations()) {
                if(donation.getUser().equals(user)) {
                    donations.add(donation);
                }
            }
        }
        return donations;
    }
}
