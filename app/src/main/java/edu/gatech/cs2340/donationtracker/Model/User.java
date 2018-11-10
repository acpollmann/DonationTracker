package edu.gatech.cs2340.donationtracker.Model;


/**
 * Implementation a location for the location list and deatil page
 * Created on 9/30/18.
 *
 * @author Group 71B
 * @version 1.0
 */

public class User {
    /** allow us to assign unique id numbers to each student */
    private static int Next_Id;

    /** this user's id number */
    private final int _id;

    /** this user's email */
    private String _email;

    /** this user's password */
    private String _password;

    /** this user's type (for now, it's a string) */
    private String _type;

    /** true if account locked false otherwise */
    private boolean _accountLocked;

    /* **********************
     * Getters and setters
     */
    //no setter for this.  id is a read only field
    /**
     * Gets id for user
     * @return id of user
     */
    public int getId() { return _id; }

    /**
     * Gets password for user
     */
    public String getPassword() {return _password; }
    /**
     * Sets password for user
     * @param password user password
     */
    public void setPassword(String password) { this._password = password; }

    /**
     * Gets email for user
     */
    public String getEmail() { return _email; }
    /**
     * Sets password for user
     * @param email user email
     */
    public void setEmail(String email) { this._email = email; }

    /**
     * Gets account type
     */
    public String getType() { return _type; }
    /**
     * Sets account type for user
     * @param type account type
     */
    public void setType(String type) { this._type = type; }

    /**
     * Gets boolean if account locked
     */
    public boolean isAccountLocked() { return _accountLocked; }
    /**
     * Sets if locked or not
     * @param locked if locked
     */
    public void setAccountLocked(boolean locked) { this._accountLocked = locked; }

    /**
     * Constructs user given only an email and password
     * @param email the user's email
     * @param password the user's password
     */
    public User(String email, String password) {
        this(email, password, "");
    }

    /**
     * Main user constructor
     * @param email the user's email
     * @param password the user's password
     * @param type the user's type
     */
    public User(String email, String password, String type) {
        this._email = email;
        this._password = password;
        this._type = type;
        this._accountLocked = false;
        this._id = User.Next_Id++;
    }

    /**
     * Uses equal method to check users
     */
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

    /**
     * Create hashcode for email and password
     */
    @Override
    public int hashCode() {
        int result = 17;
        result = (37 * result) + ((_email == null) ? 0 : _email.hashCode());
        result = (37 * result) + (((_password == null) ? 0 : _password.hashCode()));
        return result;
    }

    /**
     * Create string for email
     */
    @Override
    public String toString() {
        return this._email;
    }
}
