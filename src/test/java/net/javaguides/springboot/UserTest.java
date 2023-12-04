package net.javaguides.springboot;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import net.javaguides.springboot.model.User;

public class UserTest {
    //email contient symbole "@"
  @Test
    public void testEmailContainsAtSymbol() {
        String validEmail = "test@gmail.com";
        String invalidEmail = "invalidemail";

        User userValid = new User();
        userValid.setEmail(validEmail);
        assertTrue(userValid.getEmail().contains("@"), "Valid email should contain '@'");

        User userInvalid = new User();
        userInvalid.setEmail(invalidEmail);
        assertFalse(userInvalid.getEmail().contains("@"), "Invalid email should not contain '@'");
    }
    // email contein un symbole".com"
  
@Test
    public void testEmailContainsCom() {
        String validEmail = "test@gmail.com";
        String invalidEmail = "invalidemail";

        User userValid = new User();
        userValid.setEmail(validEmail);
        assertTrue(userValid.getEmail().contains(".com"), "Valid email should contain '.com'");

        User userInvalid = new User();
        userInvalid.setEmail(invalidEmail);
        assertFalse(userInvalid.getEmail().contains(".com"), "Invalid email should not contain '.com'");
    }



}
