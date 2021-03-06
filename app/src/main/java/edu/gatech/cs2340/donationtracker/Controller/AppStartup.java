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

        Set<User> users = FirestoreManager.getInstance().loadUsers();
        UserModel.getInstance().setUsers(users);

        List<Location> locations = FirestoreManager.getInstance().loadLocations();
        LocationModel.getInstance().setLocations(locations);

        List<Donation> donations = FirestoreManager.getInstance().loadDonations();
        DonationModel.getInstance().setDonations(donations);
    }
}
