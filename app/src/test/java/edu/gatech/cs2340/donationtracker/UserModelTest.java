package edu.gatech.cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;


import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserModel;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserModelTest {
    private UserModel testUserModel;
    private FirestoreManager mockFirestoreManager;

    private final User user1 = new User("email1", "password1");
    private final User user2 = new User("email2", "password2");
    private final User user3 = new User("email3", "password3");

    @Before
    public void setup() {
        mockFirestoreManager = mock(FirestoreManager.class);
        testUserModel = UserModel.getTestInstance(mockFirestoreManager);
    }

    @Test
    public void testAddUser() {
        testUserModel.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        assertFalse(testUserModel.getUsers().isEmpty());

        verify(mockFirestoreManager).add(user1);
    }

    @Test
    public void testUserExists_EmptySet() {
        assertTrue(testUserModel.getUsers().isEmpty());
        assertFalse(testUserModel.userExists("user"));
    }

    @Test
    public void testUserExists_OneItem_DoesExist() {
        testUserModel.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        assertFalse(testUserModel.getUsers().isEmpty());
        assertTrue(testUserModel.userExists(user1.getEmail()));
    }

    @Test
    public void testUserExists_OneItem_DoesNotExist() {
        testUserModel.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        assertFalse(testUserModel.getUsers().isEmpty());
        assertFalse(testUserModel.userExists("fake email"));
    }

    @Test
    public void testUserExists_LargerSet_DoesExist() {
        testUserModel.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        testUserModel.addUser(user2.getEmail(), user2.getPassword(), user2.getType());
        testUserModel.addUser(user3.getEmail(), user3.getPassword(), user3.getType());
        assertFalse(testUserModel.getUsers().isEmpty());
        assertTrue(testUserModel.userExists(user1.getEmail()));
        assertTrue(testUserModel.userExists(user2.getEmail()));
        assertTrue(testUserModel.userExists(user3.getEmail()));
    }

    @Test
    public void testUserExists_LargerSet_DoesNotExist() {
        testUserModel.addUser(user1.getEmail(), user1.getPassword(), user1.getType());
        testUserModel.addUser(user2.getEmail(), user2.getPassword(), user2.getType());
        testUserModel.addUser(user3.getEmail(), user3.getPassword(), user3.getType());
        assertFalse(testUserModel.getUsers().isEmpty());
        assertFalse(testUserModel.userExists("fake email"));
    }
}
