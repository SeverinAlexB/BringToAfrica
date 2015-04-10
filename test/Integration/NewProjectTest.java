package Integration;


import models.Project;
import models.Consumer;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import static play.test.Helpers.*;


public class NewProjectTest {
    @Test
    public void NewProjectTestTest(){
        DatabaseTest.runInApp(( browser -> {
            fakeRequest().withSession("email", "bob@example.com");
            browser.goTo("http://localhost:3333/projects/new");
            browser.$("[name='title']").text("Help Children get clothes");
            browser.$("[name='description']").text("Help Children get clothes");
            browser.$("[name='imageURL']").text("Some URL to Image");
            browser.$("[name='startsAt']").text("2015-03-10");
            browser.$("[name='endsAt']").text("2015-04-29");
            browser.$("[name='amountField']").text("10");
            browser.$("[name='donationField']").text("Schuhe");
            browser.$("[name='country']").text("Nigeria");
            browser.$("[name='city']").text("New York");
            browser.$("[name='contact']").text("Claudia Hofstetter\nLehstrasse 5\n 8000 Zürich");
            browser.$("#btnContinue3").click();

            Project testProject = Project.find.where().like("title", "Help Children get clothes").findUnique();
            assertEquals(testProject.getTitle(), "Help Children get clothes");
        }));
    }

}
