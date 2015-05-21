package integration;

import models.*;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;
import services.DonationGoalService;
import services.DonationService;
import services.ProjectService;

import java.util.HashMap;
import java.util.Map;

import static org.fest.assertions.Assertions.assertThat;
import static org.junit.Assert.assertTrue;
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

    @Test
    public void NewNewsTestTest(){
        DatabaseTest.runInFilledApp("testFiles/data1.yml", browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";

            browser.goTo("http://localhost:3333/registration");
            browser.getDriver().findElement(By.name("firstname")).sendKeys(firstName);
            browser.getDriver().findElement(By.name("lastname")).sendKeys(lastName);
            browser.getDriver().findElement(By.name("email")).sendKeys(email);
            browser.getDriver().findElement(By.name("password1")).sendKeys(password);
            browser.getDriver().findElement(By.name("password2")).sendKeys(password);
            browser.getDriver().findElement(By.id("btnRegistieren")).click();

            Project project = Project.find.byId(2l);
            browser.goTo("http://localhost:3333/projects/2");
            browser.getDriver().findElement(By.name("btnSpenden")).click();
            browser.getDriver().findElement(By.name("donate-custom_0")).sendKeys("10");
            browser.getDriver().findElement(By.name("remarks")).sendKeys("TestNote");
            browser.getDriver().findElement(By.id("donate-submit")).click();
            project.refresh();
            assertEquals(2, project.getDonationGoals().get(0).getDonations().size());
        });
    }
}
