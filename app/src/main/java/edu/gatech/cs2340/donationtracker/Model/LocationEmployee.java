package edu.gatech.cs2340.donationtracker.Model;

import android.location.Location;

/**
 * Location Employee Class
 * Created by amypollmann on 9/30/18.
 */

public class LocationEmployee extends User {
    private Location _location;

    public LocationEmployee(String username, String password, String email, Location location) {
        super(username, password, email);
        this._location = location;
    }

    /* **********************
    * Getters and setters
    */
    public Location getLocation() {
        return _location;
    }
    public void setLocation(Location location) {
        this._location = location;
    }
}
