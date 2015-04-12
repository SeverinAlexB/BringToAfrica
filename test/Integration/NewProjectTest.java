package Integration;


import models.Project;
import models.Consumer;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static play.test.Helpers.*;


public class NewProjectTest {
    @Test
    public void NewProjectTestTest(){
        DatabaseTest.runInCleanApp((browser -> {
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

            browser.goTo("http://localhost:3333/projects/new");
            browser.getDriver().findElement(By.name("title")).sendKeys("Help Children get clothes");
            browser.getDriver().findElement(By.name("description")).sendKeys("Help Children get clothes");

            browser.getDriver().findElement(By.name("imageURL")).sendKeys("Some URL to Image");
            browser.getDriver().findElement(By.name("startsAt")).sendKeys("2015-03-10");
            browser.getDriver().findElement(By.name("endsAt")).sendKeys("2015-04-29");
            browser.executeScript("document.getElementById('amount').value='12';");
            //browser.getDriver().findElement(By.name("amount")).sendKeys("10");
            browser.getDriver().findElement(By.name("donations[0]")).sendKeys("Schuhe");
            browser.getDriver().findElement(By.name("country")).sendKeys("Nigeria");
            browser.getDriver().findElement(By.name("city")).sendKeys("New York");
            browser.getDriver().findElement(By.name("contactInformation")).sendKeys("Email");
            browser.getDriver().findElement(By.id("btnContinue3")).click();

            assertTrue(models.Project.find.all().size() == 1);

            models.Project testProject = models.Project.find.findUnique();
            assertEquals("Help Children get clothes", testProject.getTitle());
        }));
    }

}
