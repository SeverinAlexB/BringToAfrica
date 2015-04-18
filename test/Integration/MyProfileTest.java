package Integration;

import models.User;
import org.junit.Test;
import org.openqa.selenium.By;
import services.ConsumerService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by SKU on 18.04.2015.
 */
public class MyProfileTest {
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

            browser.goTo("http://localhost:3333/myprofile");
            browser.getDriver().findElement(By.name("firstname")).clear();
            browser.getDriver().findElement(By.name("firstname")).sendKeys("Hans");
            browser.getDriver().findElement(By.name("lastname")).sendKeys("Muster");
            browser.getDriver().findElement(By.name("oldPassword")).sendKeys(password);
            browser.getDriver().findElement(By.id("btnSave")).click();

            assertEquals("Hans", User.find.findUnique().getFirstName());
        }));
    }

    @Test
    public void changePasswordTest(){
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
            browser.getDriver().findElement(By.name("lastname")).sendKeys("Muster");
            browser.getDriver().findElement(By.name("oldPassword")).sendKeys(password);
            browser.getDriver().findElement(By.id("cbx")).click();
            browser.getDriver().findElement(By.name("password1")).sendKeys(newPassword);
            browser.getDriver().findElement(By.name("password2")).sendKeys(newPassword);
            browser.getDriver().findElement(By.id("btnSave")).click();
            ConsumerService.isValid(user.getEmail(), newPassword);

            assertTrue(ConsumerService.isValid(user.getEmail(), newPassword));
        }));
    }
}
