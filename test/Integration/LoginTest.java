package Integration;

import models.Consumer;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * Created by Severin on 10.04.2015.
 */
public class LoginTest {

    @Test
    public void LoginTestTest(){
        DatabaseTest.runInFilledApp((browser -> {
            String firstName = "Michael";
            String lastName = "Blocker";
            String email = "michael.blocher@msn.com";
            String password = "MeinPw5#";
            assertTrue(Consumer.find.all().size() == 0);

            browser.goTo("http://localhost:3333/registration");
            browser.getDriver().findElement(By.name("firstname")).sendKeys(firstName);
            browser.getDriver().findElement(By.name("lastname")).sendKeys(lastName);
            browser.getDriver().findElement(By.name("email")).sendKeys(email);
            browser.getDriver().findElement(By.name("password1")).sendKeys(password);
            browser.getDriver().findElement(By.name("password2")).sendKeys(password);
            browser.getDriver().findElement(By.id("btnRegistieren")).click();

            assertTrue(Consumer.find.all().size() == 1);

            Consumer c = Consumer.find.findUnique();

            assertEquals(firstName, c.getFirstName());
            assertEquals(lastName, c.getLastName());
            assertEquals(email, c.getEmail());
            assertTrue(BCrypt.checkpw(password, c.getPasswordHashedSalted()));


        }));

    }
}
