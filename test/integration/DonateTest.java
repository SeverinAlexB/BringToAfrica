package integration;


import models.Donation;
import models.Project;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.fest.assertions.Assertions.assertThat;

public class DonateTest {
    @Test
    public void CreateDonationTest(){
        DatabaseTest.runInFilledApp("testFiles/data1.yml", browser -> {

            browser.goTo("http://localhost:3333/projects/2");
            browser.getDriver().findElement(By.name("amounts[0]")).sendKeys("2");
            browser.getDriver().findElement(By.name("remarks")).sendKeys("Die Schuhe sind etwas kaputt");
            browser.getDriver().findElement(By.id("donate-submit")).click();

            Project project = Project.find.byId(2l);
            Donation donation = project
                    .getDonationGoals().get(0)
                    .getDonations().get(2);

            assertThat(donation.getDonationGoal().getType().getName()).isEqualTo("Schuhe");
            assertThat(donation.getAmount()).isEqualTo(2);
        });
    }

}
