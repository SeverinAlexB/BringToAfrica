package Integration;

import controllers.ProjectController;
import models.*;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;
import play.twirl.api.Html;
import services.DonationGoalService;
import services.ProjectService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class ProjectWidgetTest {

    /*@Test
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
            user.save();
            int state = ProjectService.getStateOfProjectInPercent(project);
            assertEquals(5, state);
        }));
    }


    @Test
    public void  getProjectWidgetTest(){
        DatabaseTest.runInCleanApp((browser -> {
            User user = new User();
            user.setFirstName("bob");
            Project project = new Project();
            project.setTitle("TestProject");
            user.addProject(project);
            user.save();

            Html widget = ProjectController.getProjectWidget(Project.find.findUnique().getId());
            assertTrue(widget.body().contains("TestProject"));

        }));

    }*/
}
