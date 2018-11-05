package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class ListModel {

    public static final ListModel INSTANCE = new ListModel(new FirestoreManager());

    private List<LocationItem> items;

    private List<Donation> donations;

    /** The FirestoreManager responsible for saving Locations to and loading Locations from Firestore. */
    private final FirestoreManager firestoreManager;

    private ListModel(FirestoreManager firestoreManager) {
        items = new ArrayList<>();
        donations = new ArrayList<>();
        this.firestoreManager = firestoreManager;
    }

    public void addItem(LocationItem item) {
        items.add(item);
        firestoreManager.addLocation(item);
    }

    public void addDonationItem(Donation donation) {
        donations.add(donation);
        firestoreManager.addDonation(donation);
    }

    public List<LocationItem> getItems() {
        return items;
    }

    public List<Donation> getDonations() { return donations; }

    public void setLocations(List<LocationItem> locations) {
        this.items = locations;
    }

    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    public int getLocationListSize() {
        return items.size();
    }

    public LocationItem findItemById(int key) {

        for (LocationItem l : items) {
            if (l.getKey() == key) {
                return l;
            }
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}