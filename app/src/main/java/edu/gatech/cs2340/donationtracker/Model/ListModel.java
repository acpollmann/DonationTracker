package edu.gatech.cs2340.donationtracker.Model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class ListModel {
    public static final ListModel INSTANCE = new ListModel();

    private List<LocationItem> items;

    private ListModel() {
        items = new ArrayList<>();
    }

    public void addItem(LocationItem item) {
        items.add(item);
    }

    public List<LocationItem> getItems() {
        return items;
    }

    public LocationItem findItemById(int key) {


        for (LocationItem l : items) {
            if (l.getKey() == key) return l;
        }
        Log.d("MYAPP", "Warning - Failed to find key: " + key);
        return null;
    }
}



