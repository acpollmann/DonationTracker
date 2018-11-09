package edu.gatech.cs2340.donationtracker.Model;

/**
 * User Class
 * Created by amypollmann on 9/30/18.
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
    public int getId() { return _id; }

    public String getPassword() {return _password; }
    public void setPassword(String password) { this._password = password; }

    public String getEmail() { return _email; }
    public void setEmail(String email) { this._email = email; }

    public String getType() { return _type; }
    public void setType(String type) { this._type = type; }

    public boolean isAccountLocked() { return _accountLocked; }
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
    public int hashCode() {
        int result = 17;
        result = 37 * result + (_email == null ? 0 : _email.hashCode());
        result = 37 * result + (_password == null ? 0 : _password.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return this._email;
    }
}
