package application;

import org.junit.Test;
import services.ConsumerService;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ConsumerServiceTest {
    @Test
    public void validatePasswordsTest(){
        String password1 = "Test123!";
        String password2 = "Test123!";
        assertTrue(ConsumerService.validatePasswords(password1, password2));
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

}
