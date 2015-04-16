package Integration;

import models.Donation;
import models.DonationType;
import models.Project;
import models.User;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;
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
            Donation donation = new Donation();
            donation.setAmount(5);
            donation.setUser(user);
            user.addProject(project);
            project.addDonation(donation);
            assertEquals(1,Donation.find.all().size());
            System.out.println(project.getDonationGoals().get(0).getAmount());
            System.out.println(project.getDonations().get(0).getAmount());
            int state = ProjectService.getStateOfProjectInPercent(project);
            System.out.println(state);
            assertEquals(5, state);
        }));
    }


}
