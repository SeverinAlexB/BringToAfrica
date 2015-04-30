package viewmodels;

import models.DonationGoal;
import services.DonationGoalService;
import services.ProjectService;

import java.util.Date;
import java.util.List;

public class ProjectDetail {
    private String title;
    private String description;
    private String imageURL;
    private int state;
    private Date startsAt;
    private Date endsAt;
    private List<DonationGoal> donationGoals;

    public ProjectDetail(models.Project project) {
        this.title = project.getTitle();
        this.description = project.getDescription();
        this.imageURL = project.getImageURL();
        this.state = ProjectService.getStateOfProjectInPercent(project);
        this.startsAt = project.getStartsAt();
        this.endsAt = project.getEndsAt();
        this.donationGoals = project.getDonationGoals();
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageURL() {
        return imageURL;
    }

    public int getState() {
        return state;
    }

    public Date getStartsAt() {
        return startsAt;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public List<DonationGoal> getDonationGoals() {
        return donationGoals;
    }

    public int getStateOfDonationGoalInPercent(DonationGoal donationGoal) {
        return DonationGoalService.getStateInPercent(donationGoal);
    }

    public int getStateOfDonationGoal(DonationGoal donationGoal) {
        return DonationGoalService.getState(donationGoal);
    }
}
