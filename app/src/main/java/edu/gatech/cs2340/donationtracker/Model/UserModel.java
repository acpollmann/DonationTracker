package edu.gatech.cs2340.donationtracker.Model;

import java.util.HashSet;
import java.util.Set;

/**
 * Implementation user set for app
 * Created on 9/30/18.
 *
 * @author Group 71B
 * @version 1.0
 */
public class UserModel {

    private static UserModel instance;
    /**
     * Gets instance of user
     * @return instance of user
     */
    public static synchronized UserModel getInstance() {
        if (instance == null) {
            instance = new UserModel(FirestoreManager.getInstance());
        }
        return instance;
    }

    /**
     * Gets an instance of UserModel, used for testing purposes
     * @param firestoreManager an instance of FirestoreManager
     * @return an instance of UserModel
     */
    public static UserModel getTestInstance(FirestoreManager firestoreManager) {
        return new UserModel(firestoreManager);
    }

    /** Set of users known to the application. */
    private Set<User> _users;

    /** The FirestoreManager responsible for saving Users to and loading Users from Firestore. */
    private final FirestoreManager firestoreManager;

    /**
     * make a new model
     */
    private UserModel(FirestoreManager firestoreManager) {
        _users = new HashSet<>();
        this.firestoreManager = firestoreManager;
    }

    /**
     * @return the set of users stored in our app
     */
    public Set<User> getUsers() { return _users; }

    /**
     * This method is only called once on startup, when the saved users from Firestore is loaded
     * into the application.
     *
     * @param users the set of users stored in Firestore
     */
    public void setUsers(Set<User> users) {
        _users = users;
    }

    /**
     * add a user to the app
     *
     * @param email the registering user's email
     * @param password the registering user's password
     * @param type the registering user's type
     */
    public void addUser(String email, String password, String type) {
        User newUser = new User(email, password, type);
        _users.add(newUser);
        firestoreManager.add(newUser);
    }

    /**
     * Searches through the set of users in O(n) time, checking if a user with the given email
     * already exists in the set.
     *
     * @param email the registering user's email
     * @return true if a user with the given email already exists, false otherwise
     */
    public boolean userExists(String email) {
        for (User u : _users) {
            if (u.getEmail().equals(email)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Called on login, checks if the given email and password matches with a registered user
     * @param email the user's email
     * @param password the user's password
     * @return true if this is a valid user (exists in the user set), false otherwise
     */
    public boolean validUser(String email, String password) {
        return _users.contains(new User(email, password));
    }
}
