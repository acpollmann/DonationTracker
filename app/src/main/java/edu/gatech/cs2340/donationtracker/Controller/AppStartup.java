package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Application;

import java.util.List;
import java.util.Set;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;

/**
 * Implementation that will initiate ListModel and FireStore.
 *
 * @author Group 71B
 * @version 1.0
 */

public class AppStartup extends Application {
    //FirestoreManager firestoreManager;
    ListModel model = ListModel.getInstance();
    @Override
    public void onCreate() {
        super.onCreate();

        FirestoreManager firestoreManager;

        //ListModel model = ListModel.getInstance();


        firestoreManager = new FirestoreManager();
        Set<User> users = firestoreManager.loadUsers();
        UserSet.getInstance().setUsers(users);


        List<Location> locations = firestoreManager.loadLocations();
        model.setLocations(locations);
        //ListModel.getInstance().setLocations(locations);

        List<Donation> donations = firestoreManager.loadDonations();
        model.setDonations(donations);
        //ListModel.getInstance().setLocations(locations);

    }
}
