package Integration;

import models.User;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertTrue;

public class LoginTest {

    @Test
    public void LoginTest(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(User.find.all().size() == 1);
            assertTrue(browser.getCookies().size() == 0);

            browser.goTo("http://localhost:3333/login");
            browser.getDriver().findElement(By.name("email")).sendKeys("bob@gmail.com");
            browser.getDriver().findElement(By.name("password")).sendKeys("secret");
            browser.getDriver().findElement(By.id("btnLogin")).click();

            assertTrue(browser.getCookies().size() == 1);
        }));
    }

    @Test
    public void wrongLoginTest(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(User.find.all().size() == 1);
            assertTrue(browser.getCookies().size() == 0);

            browser.goTo("http://localhost:3333/login");
            browser.getDriver().findElement(By.name("email")).sendKeys("bob@gmail.com");
            browser.getDriver().findElement(By.name("password")).sendKeys("secretasdfasdfasdf");
            browser.getDriver().findElement(By.id("btnLogin")).click();


            assertTrue(browser.getCookies().size() == 0);
        }));
    }

}
