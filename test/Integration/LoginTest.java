package Integration;

import models.Consumer;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;


import java.util.Set;

import static org.junit.Assert.assertTrue;

/**
 * Created by Severin on 10.04.2015.
 */
public class LoginTest {

    @Test
    public void LoginTest(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(Consumer.find.all().size() == 1);
            assertTrue(browser.getCookies().size() == 0);

            browser.goTo("http://localhost:3333/login");
            browser.getDriver().findElement(By.name("email")).sendKeys("bob@gmail.com");
            browser.getDriver().findElement(By.name("password")).sendKeys("secret");
            browser.getDriver().findElement(By.id("btnLogin")).click();

            assertTrue(browser.getCookies().size() == 1);
        }));
    }

    @Test
    public void LoginTest2(){
        DatabaseTest.runInFilledApp((browser -> {
            assertTrue(Consumer.find.all().size() == 1);
            assertTrue(browser.getCookies().size() == 0);

            browser.goTo("http://localhost:3333/login");
            browser.getDriver().findElement(By.name("email")).sendKeys("bob@gmail.com");
            browser.getDriver().findElement(By.name("password")).sendKeys("secretasdfasdfasdf");
            browser.getDriver().findElement(By.id("btnLogin")).click();


            assertTrue(browser.getCookies().size() == 0);
        }));
    }

}
