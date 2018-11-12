package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Application;

import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.DonationModel;
import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.LocationModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserModel;

/**
 * Implementation that will initiate LocationModel and FireStore.
 *
 * @author Group 71B
 * @version 1.0
 */

public class AppStartup extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        FirestoreManager firestoreManager = new FirestoreManager();

        Set<User> users = firestoreManager.loadUsers();
        UserModel.getInstance().setUsers(users);

        List<Location> locations = firestoreManager.loadLocations();
        LocationModel.getInstance().setLocations(locations);

        List<Donation> donations = firestoreManager.loadDonations();
        DonationModel.getInstance().setDonations(donations);
    }
}
