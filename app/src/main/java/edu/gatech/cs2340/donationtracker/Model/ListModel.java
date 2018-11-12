package edu.gatech.cs2340.donationtracker.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation the list for donation and location for app
 *
 * @author Group 71B
 * @version 1.0
 */
public class ListModel {

    private static ListModel instance;

    /**
     * gets the instance of list model for location and donation
     * @return database instance
     */
    public static synchronized ListModel getInstance() {
        if (instance == null) {
            instance =  new ListModel(new FirestoreManager());
        }
        return instance;
    }

    /**
     * Gets an instance of UserModel, used for testing purposes
     * @param firestoreManager an instance of FirestoreManager
     * @return an instance of UserModel
     */
    public static ListModel getTestInstance(FirestoreManager firestoreManager) {
        return new ListModel(firestoreManager);
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
     * @param latitude what required of a location
     * @param longitude what required of a location
     * @param street what required of a location
     * @param city what required of a location
     * @param state what required of a location
     * @param zip what required of a location
     * @param type what required of a location
     * @param phone what required of a location
     * @param web what required of a location
     * @param name what required of a location
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
     * Adds a location to the location list for application
     * @param location the location to add
     */
    public void addLocation(Location location) {
        locations.add(location);
        firestoreManager.addLocation(location);
    }

    /**
     * Adds a donation to the donation list for application
     * @param location what required of a donation
     * @param timeStamp what required of a donation
     * @param shortDescription what required of a donation
     * @param fullDescription what required of a donation
     * @param value what required of a donation
     * @param category what required of a donation
     * @param comment what required of a donation
     * @param name what required of a donation
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
     * @return list of locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Gets the donation list for application
     * @return list of donations
     */
    public List<Donation> getDonations() { return donations; }

    /**
     * Sets the location list for application
     * @param locations list of locations
     */
    public void setLocations(List<Location> locations) {
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
     * @return size of list of locations
     */
    int getLocationListSize() {
        return locations.size();
    }

    /**
     * Gets the size of donation list for application
     * @return size of list of donations
     */
    public int getDonationListSize() {
        return donations.size();
    }

    /**
     * finds item by the key value
     * @param key id for item
     * @return location from id
     */
    public Location findLocationById(int key) {

        for (Location l : locations) {
            if (l.getKey() == key) {
                return l;
            }
        }

        return null;
    }
}
