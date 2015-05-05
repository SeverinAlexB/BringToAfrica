package integration;

import models.User;
import org.junit.Test;
import org.openqa.selenium.By;
import play.mvc.Result;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.fest.assertions.Assertions.assertThat;
import static play.test.Helpers.contentAsString;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;

public class NewProjectTest {

    @Test
    public void CreateDonationValidationTest() {
        DatabaseTest.runInCleanApp(( browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            user.setPasswordHashedSalted(password);
            user.save();


            Map<String, String> map = new HashMap<>();
            map.put("title", "Help Children get clothes");
            map.put("description", "Test Description");
            map.put("imgURL", "Some URL to Image");
            map.put("startsAT", "test");
            map.put("endsAt", "2015-04-29");
            map.put("amounts", "11");
            map.put("donations", "Schuhe");
            map.put("country", "Amerika");
            map.put("city", "New York");

            Result result = callAction(
                    controllers.routes.ref.ProjectController.addProjectData(),
                    fakeRequest().withSession("email", email).withFormUrlEncodedBody(map));
            String test = contentAsString(result);
            assertEquals(BAD_REQUEST, status(result));
            assertTrue(contentAsString(result).contains("Bitte fülle deine Kontaktmöglichkeiten aus"));
        }));
    }

    @Test
    public void NewProjectTestTest(){
        DatabaseTest.runInCleanApp((browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";

            assertTrue(User.find.all().size() == 0);

            browser.goTo("http://localhost:3333/registration");
            browser.getDriver().findElement(By.name("firstname")).sendKeys(firstName);
            browser.getDriver().findElement(By.name("lastname")).sendKeys(lastName);
            browser.getDriver().findElement(By.name("email")).sendKeys(email);
            browser.getDriver().findElement(By.name("password1")).sendKeys(password);
            browser.getDriver().findElement(By.name("password2")).sendKeys(password);
            browser.getDriver().findElement(By.id("btnRegistieren")).click();

            assertTrue(User.find.all().size() == 1);

            browser.goTo("http://localhost:3333/projects/new");
            browser.getDriver().findElement(By.name("title")).sendKeys("Help Children get clothes");
            browser.getDriver().findElement(By.name("description")).sendKeys("Help Children get clothes");

            browser.getDriver().findElement(By.name("imageURL")).sendKeys("Some URL to Image");
            browser.getDriver().findElement(By.name("startsAt")).sendKeys("2015-03-10");
            browser.getDriver().findElement(By.name("endsAt")).sendKeys("2015-04-29");
            //browser.executeScript("document.getElementById('amount').value='12';");
            browser.getDriver().findElement(By.name("amounts[0]")).sendKeys("10");
            browser.getDriver().findElement(By.name("donations[0]")).sendKeys("Schuhe");
            browser.getDriver().findElement(By.name("country")).sendKeys("Nigeria");
            browser.getDriver().findElement(By.name("city")).sendKeys("New York");
            browser.getDriver().findElement(By.name("contactInformation")).sendKeys("Email");
            browser.getDriver().findElement(By.id("btnContinue3")).click();

            assertThat(models.Project.find.all().size() == 1);

            models.Project testProject = models.Project.find.findUnique();
            assertEquals("Help Children get clothes", testProject.getTitle());
        }));
    }

}
