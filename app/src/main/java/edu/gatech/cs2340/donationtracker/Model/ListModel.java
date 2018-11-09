package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListModel {

    public static ListModel instance;
    public static ListModel getInstance() {
        if (instance == null) {
            instance = new ListModel(new FirestoreManager());
        }

        return instance;
    }

    private List<Location> items;

    private List<Donation> donations;

    /** The FirestoreManager responsible for saving Locations to and loading Locations
     * from Firestore. */
    private final FirestoreManager firestoreManager;

    private ListModel(FirestoreManager firestoreManager) {
        items = new ArrayList<>();
        donations = new ArrayList<>();
        this.firestoreManager = firestoreManager;
    }

    public void addItem(Location item) {
        items.add(item);
        firestoreManager.addLocation(item);
    }

    public void addDonationItem(Donation donation) {
        donations.add(donation);
        firestoreManager.addDonation(donation);
    }

    public List<Location> getItems() {
        return items;
    }

    public List<Donation> getDonations() { return donations; }

    public void setLocations(List<Location> locations) {
        this.items = locations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public int getLocationListSize() {
        return items.size();
    }

    public Location findItemById(int key) {

        for (Location l : items) {
            if (l.getKey() == key) {
                return l;
            }
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}
