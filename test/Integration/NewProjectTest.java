package Integration;


import models.Project;
import models.Consumer;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;

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
            browser.$("[name='title']").text("Help Children get clothes");
            browser.$("[name='description']").text("Help Children get clothes");
            browser.$("[name='imageURL']").text("Some URL to Image");
            browser.$("[name='startsAt']").text("2015-03-10");
            browser.$("[name='endsAt']").text("2015-04-29");
            browser.$("[name='amounts[0]']").text("10");
            browser.$("[name='donations[0]']").text("Schuhe");
            browser.$("[name='country']").text("Nigeria");
            browser.$("[name='city']").text("New York");
            browser.$("[name='contactInformation']").text("Claudia Hofstetter\nLehstrasse 5\n 8000 Zürich");
            browser.$("#btnContinue3").click();

            assertTrue(Project.find.all().size() == 1);

            Project testProject = Project.find.where().findUnique();
            assertNotNull(testProject);
        }));
    }

}
