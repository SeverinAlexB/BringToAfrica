package integration;

import models.User;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.openqa.selenium.By;
import play.mvc.Result;
import services.ConsumerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import static play.test.Helpers.*;


public class MyProfileTest {

    @Test
    public void changePassword(){
        DatabaseTest.runInCleanApp(( browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";
            String newPassword = "Test123!";

            User user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setEmail(email);
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());
            user.setPasswordHashedSalted(hash);
            user.save();

            Map<String, String> map = new HashMap<>();
            map.put("firstname", "Hans");
            map.put("lastname", "Muster");
            map.put("email", email);
            map.put("id", user.getId().toString());
            map.put("oldPassword", password);
            map.put("password1", newPassword);
            map.put("password2", newPassword);
            map.put("changePassword", "true");

            Result result = callAction(
                    controllers.routes.ref.MyProfileController.saveMyProfile(),
                    fakeRequest().withSession("email", email).withFormUrlEncodedBody(map));
            String test = contentAsString(result);
            assertEquals(303, status(result));
            assertTrue(ConsumerService.isValid(user.getEmail(), newPassword));
        }));
    }

    @Test
    public void changeNameTest(){
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
            User user = User.find.findUnique();
            assertEquals(lastName, user.getLastName());

            String newPassword = "Test1234!";

            browser.goTo("http://localhost:3333/myprofile");
            browser.getDriver().findElement(By.name("firstname")).clear();
            browser.getDriver().findElement(By.name("firstname")).sendKeys("Hans");

            assertEquals("Hans", browser.getDriver().findElement(By.name("firstname")).getAttribute("value"));

            browser.getDriver().findElement(By.name("oldPassword")).sendKeys(password);
            browser.getDriver().findElement(By.id("cbx")).click();

            assertEquals("false", browser.getDriver().findElement(By.id("cbx")).getAttribute("value"));

            browser.getDriver().findElement(By.name("password1")).sendKeys(newPassword);
            browser.getDriver().findElement(By.name("password2")).sendKeys(newPassword);
            browser.getDriver().findElement(By.id("btnSave")).click();
            assertEquals("Hans", User.find.findUnique().getFirstName());
        }));
    }
}
