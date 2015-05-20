package integration;

import models.DonationGoal;
import models.DonationType;
import models.Project;
import models.User;
import org.junit.Test;
import play.mvc.Result;
import services.DonationGoalService;
import services.ProjectService;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;
import static org.junit.Assert.assertEquals;

public class DonateTest {
    @Test
    public void CreateDonationTest() {
        DatabaseTest.runInFilledApp((browser -> {
            String email = "bob@gmail.com";
            Map<String, String> map = new HashMap<>();
            map.put("amounts", "10");
            map.put("donations", "Schuhe");
            map.put("remarks", "Test");
            map.put("projectId", "10");

            Project project = ProjectService.getProjectById(10l);
            DonationGoal goal = new DonationGoal();
            DonationType type = new DonationType();
            type.setName("Schuhe");
            type.save();
            goal.setAmount(100);
            goal.setType(type);
            goal.setProject(project);

            goal.save();
            project.save();

            Result result = callAction(
                    controllers.routes.ref.DonationController.donate(),
                    fakeRequest().withSession("email", email).withFormUrlEncodedBody(map));
            assertEquals(200, status(result));
            goal.refresh();
            assertEquals(10, DonationGoalService.getState(goal));
        }));
    }
}
