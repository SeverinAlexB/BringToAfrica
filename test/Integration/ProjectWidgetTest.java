package Integration;

import models.*;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;
import services.DonationGoalService;
import services.ProjectService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProjectWidgetTest {

    @Test
    public void WidgetTest(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(Project.find.all().size() == 1);
            assertTrue(User.find.all().size() == 1);
            Project project = Project.find.findUnique();
            User user = User.find.findUnique();
            DonationType donationType = new DonationType();
            donationType.setName("test");
            DonationGoal donationGoal = new DonationGoal(project);
            donationGoal.setAmount(100);
            Donation donation = new Donation(user, donationGoal);
            donation.setAmount(5);
            donationType.addDonationGoal(donationGoal);
            donationType.save();
            donationGoal.save();
            donation.save();
            donation.setUser(user);
            user.addProject(project);
<<<<<<< HEAD
            project.addDonation(donation);
            assertEquals(1,Donation.find.all().size());
            System.out.println(project.getDonationGoals().get(0).getAmount());
            System.out.println(project.getDonations().get(0).getAmount());
=======
            user.save();
>>>>>>> origin/ProjectWidget
            int state = ProjectService.getStateOfProjectInPercent(project);
            System.out.println(state);
            assertEquals(5, state);
        }));
    }


}
