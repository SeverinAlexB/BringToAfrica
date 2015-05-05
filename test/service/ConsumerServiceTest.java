package service;

import integration.DatabaseTest;
import junit.framework.TestCase;
import models.User;
import org.junit.Assert;
import org.junit.Test;
import org.mindrot.jbcrypt.BCrypt;
import services.ConsumerService;

import static com.thoughtworks.selenium.SeleneseTestBase.assertTrue;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ConsumerServiceTest {

    @Test
    public void getConsumerByEmailTest() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            User c = new User();
            c.setEmail(mail);
            c.save();
            User c2 = ConsumerService.getConsumerByEmail(mail.toUpperCase());
            assertEquals(c2.getId(), c.getId());
        });
    }
    @Test
    public void getConsumerByEmailNullTest() {
        DatabaseTest.runInCleanApp(browser -> {
            User c2 = ConsumerService.getConsumerByEmail(null);
            assertNull(c2);
        });
    }
    @Test
    public void isValidTest() {
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            String password = "MeinPw5#";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            User c = new User();
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

            User c = new User();
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

            User c = new User();
            c.setEmail(mail);
            c.setPasswordHashedSalted(hash);
            c.save();

            assertTrue(!ConsumerService.isValid(mail.toUpperCase() + "e", password));
        });
    }

    @Test
    public void validatePasswordsTest(){
        String password1 = "Test123!";
        String password2 = "Test123!";
        Assert.assertTrue(ConsumerService.validatePasswords(password1, password2));
    }

    @Test
    public void validatePasswordsFailsTest(){
        String password1 = "Test123";
        String password2 = "Test123";
        assertFalse(ConsumerService.validatePasswords(password1, password2));
    }

    @Test
    public void validatePasswordsFailShortTest(){
        String password1 = "Test1!";
        String password2 = "Test1!";
        assertFalse(ConsumerService.validatePasswords(password1, password2));
    }

    @Test
    public void changePasswordTest(){
        DatabaseTest.runInCleanApp(browser -> {
            String mail = "marc.oberholzer@hotmail.com";
            String password = "MeinPw5#";
            String newPassword = "Test123!";
            String hash = BCrypt.hashpw(password, BCrypt.gensalt());

            User c = new User();
            c.setEmail(mail);
            c.setPasswordHashedSalted(hash);
            c.save();
            assertTrue(ConsumerService.isValid(mail.toUpperCase(), password));
            assertTrue(ConsumerService.validatePasswords(newPassword, newPassword));

            ConsumerService.changePassword(c, password, newPassword);
            assertTrue(ConsumerService.isValid(c.getEmail(), newPassword));
        });

    }

}
