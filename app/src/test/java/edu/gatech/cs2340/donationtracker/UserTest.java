package edu.gatech.cs2340.donationtracker;

import org.junit.Test;

import edu.gatech.cs2340.donationtracker.Model.User;

import static org.junit.Assert.assertEquals;

public class UserTest {

    @Test
    public void testCreate() {
        String email = "test@example.com";
        String password = "testpassword";
        String type = "User";

        User user = new User(email, password, type);

        assertEquals(email, user.getEmail());
        assertEquals(password, user.getPassword());
        assertEquals(type, user.getType());
    }
}
