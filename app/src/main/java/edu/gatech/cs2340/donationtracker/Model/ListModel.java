package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListModel {

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

    public void addLocation(Location item) {
        locations.add(item);
        firestoreManager.addLocation(item);
    }

    public void addDonation(String name, Location location, String timeStamp,
                            String shortDescription, String fullDescription, String value,
                            String category, String comment) {
        Donation newDonation = new Donation(name, location, timeStamp, shortDescription,
                fullDescription, value, category, comment);
        donations.add(newDonation);
        firestoreManager.addDonation(newDonation);
    }

    public List<Location> getLocations() {
        return locations;
    }

    public List<Donation> getDonations() { return donations; }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public int getLocationListSize() {
        return locations.size();
    }

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
