package service;

import integration.DatabaseTest;
import models.Donation;
import models.DonationType;
import org.junit.Test;

/**
 * Created by Severin on 16.04.2015.
 */
public class DonationControllerTest {

    @Test
    public void getByUserTest(){
        DatabaseTest.runInFilledApp(testBrowser -> {
            DonationType type = new DonationType();
            type.setName("Schuhe");
            type.save();

            Donation donation = new Donation();

        });
    }
}
