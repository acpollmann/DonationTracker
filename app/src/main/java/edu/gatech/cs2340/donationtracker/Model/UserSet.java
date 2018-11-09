package edu.gatech.cs2340.donationtracker.Model;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
    /** Singleton instance */
    private static final UserSet _instance = new UserSet(new FirestoreManager());
    public static UserSet getInstance() { return _instance; }

    /** Set of users known to the application. */
    private Set<User> _users;

    /** The FirestoreManager responsible for saving Users to and loading Users from Firestore. */
    private final FirestoreManager firestoreManager;

    /**
     * make a new model
     */
    private UserSet(FirestoreManager firestoreManager) {
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
}
