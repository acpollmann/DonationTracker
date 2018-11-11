package edu.gatech.cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;

public class UserSetTest {
    private UserSet testUserSet;

    private final User user1 = new User("email1", "password1");
    private final User user2 = new User("email2", "password2");
    private final User user3 = new User("email3", "password3");

    @Before
    public void setup() {
        testUserSet = UserSet.getTestInstance(mock(FirestoreManager.class));
    }

    @Test
    public void testUserExists_EmptySet() {
        assertTrue(testUserSet.getUsers().isEmpty());
        assertFalse(testUserSet.userExists("user"));
    }

    @Test
    public void testUserExists_OneItem_DoesExist() {
        testUserSet.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        assertFalse(testUserSet.getUsers().isEmpty());
        assertTrue(testUserSet.userExists(user1.getEmail()));
    }

    @Test
    public void testUserExists_OneItem_DoesNotExist() {
        testUserSet.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        assertFalse(testUserSet.getUsers().isEmpty());
        assertFalse(testUserSet.userExists("fake email"));
    }

    @Test
    public void testUserExists_LargerSet_DoesExist() {
        testUserSet.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        testUserSet.addUser(user2.getEmail(), user2.getPassword(), user2.getType());
        testUserSet.addUser(user3.getEmail(), user3.getPassword(), user3.getType());
        assertFalse(testUserSet.getUsers().isEmpty());
        assertTrue(testUserSet.userExists(user1.getEmail()));
        assertTrue(testUserSet.userExists(user2.getEmail()));
        assertTrue(testUserSet.userExists(user3.getEmail()));
    }

    @Test
    public void testUserExists_LargerSet_DoesNotExist() {
        testUserSet.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        testUserSet.addUser(user2.getEmail(), user2.getPassword(), user2.getType());
        testUserSet.addUser(user3.getEmail(), user3.getPassword(), user3.getType());
        assertFalse(testUserSet.getUsers().isEmpty());
        assertFalse(testUserSet.userExists("fake email"));
    }
}
