package integration;


import models.Donation;
import models.User;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class DonateTest {
    @Test
    public void CreateDonationTest(){
        DatabaseTest.runInCleanApp(browser -> {

            browser.goTo("http://localhost:3333/projects/2");
            browser.getDriver().findElement(By.name("amounts[0]")).sendKeys("2");
            browser.getDriver().findElement(By.name("remarks")).sendKeys("Die Schuhe sind etwas kaputt");
            browser.getDriver().findElement(By.id("donate-submit")).click();

            assertTrue(Donation.find.all().size() == 1);
            Donation donation = Donation.find.findUnique();

            assertEquals(2, donation.getAmount());
            assertEquals("Schuhe", donation.getDonationGoal().getType().getName());
            assertEquals(new Long(2), donation.getDonationGoal().getProject().getId());
        });
    }

}
