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
            assertTrue(Project.find.all().size() > 0);
            assertTrue(User.find.all().size() >0 );
            Project project = Project.find.all().get(0);
            User user = User.find.all().get(0);

            DonationType donationType = new DonationType();
            donationType.setName("test");
            donationType.save();

            DonationGoal donationGoal = new DonationGoal(project);
            donationGoal.setAmount(100);
            donationGoal.setType(donationType);
            donationGoal.save();

            Donation donation = new Donation(user, donationGoal);
            donation.setAmount(5);
            donation.setDonationGoal(donationGoal);
            donation.setUser(user);

            donation.save();

            project.refresh();
            int state = ProjectService.getStateOfProjectInPercent(project); //Double anstatt Int für diese Funktion verwenden, sonst stimmt das Resultat bei grossen Zahlen nicht mehr!
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
            project.setOwner(user);
            user.save();
            project.save();

            Html widget = ProjectController.getProjectWidget(Project.find.findUnique().getId());
            assertTrue(widget.body().contains("TestProject"));

        }));

    }*/
}
