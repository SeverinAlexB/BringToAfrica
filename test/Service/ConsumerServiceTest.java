package Service;

import Integration.DatabaseTest;
import models.Consumer;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import services.ConsumerService;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;

/**
 * Created by Severin on 10.04.2015.
 */
public class ConsumerServiceTest {

    @Test
    public void getConsumerByEmailTest() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            Consumer c = new Consumer();
            c.setEmail(mail);
            c.save();
            Consumer c2 = ConsumerService.getConsumerByEmail(mail.toUpperCase());
            assertEquals(c2.getId(), c.getId());
        });
    }
    @Test
    public void getConsumerByEmailNullTest() {
        DatabaseTest.runInCleanApp(browser -> {
            Consumer c2 = ConsumerService.getConsumerByEmail(null);
            assertNull(c2);
        });
    }
    @Test
    public void isValidTest() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            String password = "MeinPw5#";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            Consumer c = new Consumer();
            c.setEmail(mail);
            c.setPasswordHashedSalted(hash);
            c.save();

            assertTrue(ConsumerService.isValid(mail.toUpperCase(), password));
        });
    }
    @Test
    public void isValidTest2() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            String password = "MeinPw5#";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            Consumer c = new Consumer();
            c.setEmail(mail);
            c.setPasswordHashedSalted(hash);
            c.save();

            assertTrue(!ConsumerService.isValid(mail.toUpperCase(), password + "1"));
        });
    }
    @Test
    public void isValidTest3() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            String password = "MeinPw5#";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            Consumer c = new Consumer();
            c.setEmail(mail);
            c.setPasswordHashedSalted(hash);
            c.save();

            assertTrue(!ConsumerService.isValid(mail.toUpperCase() + "e", password));
        });
    }



}
