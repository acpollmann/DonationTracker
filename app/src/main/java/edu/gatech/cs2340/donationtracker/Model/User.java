package edu.gatech.cs2340.donationtracker.Model;


import android.support.annotation.NonNull;

/**
 * Implementation user for app
 * Created on 9/30/18.
 *
 * @author Group 71B
 * @version 1.0
 */

public class User {
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

    /**
     * Gets password for user
     * @return password of user
     */
    public String getPassword() {return _password; }
    /**
     * Sets password for user
     * @param password user password
     */
    public void setPassword(String password) { this._password = password; }

    /**
     * Gets email for user
     * @return email of user
     */
    public String getEmail() { return _email; }
    /**
     * Sets password for user
     * @param email user email
     */
    public void setEmail(String email) { this._email = email; }

    /**
     * Gets account type
     * @return account type of user
     */
    public String getType() { return _type; }
    /**
     * Sets account type for user
     * @param type account type
     */
    public void setType(String type) { this._type = type; }

    /**
     * Gets boolean if account locked
     * @return if user locked
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
    }

    /**
     * Uses equal method to check users
     * @return if equals
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
     * @return hashcode of user
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
     * @return string of email
     */
    @NonNull
    @Override
    public String toString() {
        return this._email;
    }
}
