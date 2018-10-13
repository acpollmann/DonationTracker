package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListModel {
    public static final ListModel INSTANCE = new ListModel();

    private List<LocationItem> items;

    private List<Donation> donations;

    private ListModel() {
        items = new ArrayList<>();
        donations = new ArrayList<>();
    }

    public void addItem(LocationItem item) {
        items.add(item);
    }

    public void addDonationItem(Donation donation) { donations.add(donation); }

    public List<LocationItem> getItems() {
        return items;
    }

    public List<Donation> getDonations() { return donations; }

    public LocationItem findItemById(int key) {

        for (LocationItem l : items) {
            if (l.getKey() == key) return l;
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
    public Donation findDonationById(int key) {

        for (Donation d : donations) {
            if (d.getKey() == key) return d;
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}



