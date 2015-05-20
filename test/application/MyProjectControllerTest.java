package application;

import integration.DatabaseTest;
import models.DonationGoal;
import models.DonationType;
import models.Project;
import org.junit.Test;
import play.mvc.Http;
import play.mvc.Result;
import services.ProjectService;

import static play.test.Helpers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MyProjectControllerTest {
    @Test
    public void getDataTest() {
        DatabaseTest.runInFilledApp((browser -> {
            String email = "bob@gmail.com";

            Project project = ProjectService.getProjectById(10l);

            Result result = callAction(
                    controllers.routes.ref.MyProjectController.myProjects(),
                    fakeRequest().withSession("email", email));
            assertEquals(200, status(result));
        }));
    }
}
