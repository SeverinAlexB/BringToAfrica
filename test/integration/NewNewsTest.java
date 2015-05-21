package integration;


import models.News;
import models.Project;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.fest.assertions.Assertions.assertThat;

public class NewNewsTest {
    @Test
    public void NewNewsTestTest(){
        DatabaseTest.runInFilledApp("testFiles/data1.yml", browser -> {

            browser.goTo("http://localhost:3333/projects/2");
            browser.getDriver().findElement(By.name("title")).sendKeys("Test News");
            browser.getDriver().findElement(By.name("description")).sendKeys("Hallo Welt");
            browser.getDriver().findElement(By.name("imageUrl")).sendKeys("http://www.bugatti.com/pages/1405/bildspalte/vorschaubilder_5.jpg");
            browser.getDriver().findElement(By.id("news-submit")).click();

            Project project = Project.find.byId(2l);
            News news = project.getNews().get(0);
            assertThat(news.getTitle().contentEquals("Test News"));

        });
    }

}
