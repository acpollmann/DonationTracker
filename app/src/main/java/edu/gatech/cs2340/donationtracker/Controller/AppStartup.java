package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Application;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;
import edu.gatech.cs2340.donationtracker.R;

public class AppStartup extends Application {
    private ListModel model;
    private FirestoreManager firestoreManager;

    @Override
    public void onCreate() {
        super.onCreate();

        model = ListModel.INSTANCE;


        firestoreManager = new FirestoreManager();
        Set<User> users = firestoreManager.loadUsers();
        UserSet.getInstance().setUsers(users);


        List<LocationItem> locations = firestoreManager.loadLocations();
        model.setLocations(locations);

        List<Donation> donations = firestoreManager.loadDonations();
        model.setDonations(donations);

    }
}
