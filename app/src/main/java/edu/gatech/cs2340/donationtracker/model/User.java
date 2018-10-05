package edu.gatech.cs2340.donationtracker.model;

/**
 * User Class
 * Created by amypollmann on 9/30/18.
 */

public class User {
    /** allow us to assign unique id numbers to each student */
    private static int Next_Id = 0;

    /** this user's id number */
    private int _id;

    /** this user's password */
    private String _password;

    /** this user's email */
    private String _email;

    /** true if account locked false otherwise */
    private boolean _accountLocked;

    /* **********************
     * Getters and setters
     */
    //no setter for this.  id is a read only field
    public int getId() { return _id; }

    public String getPassword() {return _password; }
    public void setPassword(String password) { this._password = password; }

    public String getEmail() { return _email; }
    public void setEmail(String email) { this._email = email; }

    public boolean isAccountLocked() { return _accountLocked; }
    public void setAccountLocked(boolean locked) { this._accountLocked = locked; }

    /**
     * User constructor
     * @param password the user's password
     * @param email the user's email
     */
    public User(String password, String email) {
        this._password = password;
        this._email = email;
        this._accountLocked = false;
        this._id = User.Next_Id++;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof User)) {
            return false;
        }
        User u = (User)obj;
        return this._email.equals(u.getEmail()) && this._password.equals(u.getPassword());
    }

    @Override
    public String toString() {
        return this._email;
    }
}
