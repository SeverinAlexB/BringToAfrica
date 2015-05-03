package application;

import integration.DatabaseTest;
import models.Donation;
import models.DonationType;
import org.junit.Test;
import play.Play;

import java.io.InputStream;


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
