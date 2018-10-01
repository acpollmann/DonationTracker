package edu.gatech.cs2340.donationtracker.Model;

import java.util.HashSet;
import java.util.Set;

public class UserSet {
    /** Singleton instance */
    private static final UserSet _instance = new UserSet();
    public static UserSet getInstance() { return _instance; }

    private Set<User> _users;

    /**
     * make a new model
     */
    private UserSet() {
        _users = new HashSet<>();
    }

    /**
     *
     * @return the set of users stored in our app
     */
    public Set<User> getUsers() { return _users; }

    /**
     * add a user to the app.  checks if the user is already entered
     *
     * uses O(n) linear search for user
     *
     * @param user  the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean addUser(User user) {
       if (_users.contains(user)) {
           return false;
       }
       _users.add(user);
       return true;
    }
}
