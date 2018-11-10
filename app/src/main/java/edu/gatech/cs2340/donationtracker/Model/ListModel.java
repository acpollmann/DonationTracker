package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation the list for donation and location for app
 *
 * @author Group 71B
 * @version 1.0
 */
public final class ListModel {

    private static ListModel instance;
    public static ListModel getInstance() {
        if (instance == null) {
            instance = new ListModel(new FirestoreManager());
        }

        return instance;
    }

    private List<Location> locations;

    private List<Donation> donations;

    /** The FirestoreManager responsible for saving Locations to and loading Locations
     * from Firestore. */
    private final FirestoreManager firestoreManager;

    private ListModel(FirestoreManager firestoreManager) {
        locations = new ArrayList<>();
        donations = new ArrayList<>();
        this.firestoreManager = firestoreManager;
    }

    /**
     * Adds a location to the location list for application
     * @param name,latitude,longitude,street,city,state,zip,type,phone,web what
     * required of a location
     */
    public void addLocation(String name, String latitude, String longitude,
                            String street, String city, String state, String zip, String type,
                            String phone, String web) {
        Location newLocation = new Location(name, Double.parseDouble(latitude),
                Double.parseDouble(longitude), street, city, state, Integer.parseInt(zip),
                type, phone, web);
        locations.add(newLocation);
        firestoreManager.addLocation(newLocation);
    }

    /**
     * Adds a donation to the donation list for application
     * @param name,location,timeStamp,shortDescription,fullDescription,value,category,comment what
     * required of a donation
     */
    public void addDonation(String name, Location location, String timeStamp,
                            String shortDescription, String fullDescription, String value,
                            String category, String comment) {
        Donation newDonation = new Donation(name, location, timeStamp, shortDescription,
                fullDescription, value, category, comment);
        donations.add(newDonation);
        firestoreManager.addDonation(newDonation);
    }

    /**
     * Gets the list of locations for the the application
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Gets the donation list for application
     */
    public List<Donation> getDonations() { return donations; }

    /**
     * Sets the location list for application
     * @param locations list of locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
        this.locations = locations;
    }

    /**
     * Sets the donation list for application
     * @param donations list of donations
     */
    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    /**
     * Gets the size of location list for application
     */
    public int getLocationListSize() {
        return locations.size();
    }

    /**
     * finds item by the key value
     * @param key id for item
     */
    public Location findItemById(int key) {

        for (Location l : locations) {
            if (l.getKey() == key) {
                return l;
            }
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}
