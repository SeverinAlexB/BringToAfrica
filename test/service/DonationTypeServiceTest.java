package service;
import integration.DatabaseTest;
import models.DonationType;
import org.junit.Test;
import services.DonationTypeService;
import static org.junit.Assert.assertEquals;

public class DonationTypeServiceTest {
    @Test
    public void getOrSetDonationTypeNewTest(){
        DatabaseTest.runInCleanApp(testBrowser -> {
            assertEquals(0, DonationType.find.all().size());
            DonationTypeService.getOrSetDonationType("Hosen");
            assertEquals(1, DonationType.find.all().size());
        });
    }

    @Test
    public void getOrSetDonationTypeSetTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
                assertEquals(0, DonationType.find.all().size());
                DonationTypeService.getOrSetDonationType("Hosen");
                DonationTypeService.getOrSetDonationType("Schuhe");
                assertEquals(2, DonationType.find.all().size());
        });
    }

    @Test
    public void getOrSetDonationTypeGetTest() {
        DatabaseTest.runInCleanApp(testBrowser -> {
            assertEquals(0, DonationType.find.all().size());
            DonationTypeService.getOrSetDonationType("Hosen");
            DonationTypeService.getOrSetDonationType("Hosen");
            assertEquals(1, DonationType.find.all().size());
        });
    }
}
