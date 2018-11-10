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
    private FirestoreManager firestoreManager;

    /**
     * On the creation of the app it will create the list model
     * and set up the fireStore. It will add the lists of donations
     * and locations to fireStore.
     */
    @Override
    public void onCreate() {
        super.onCreate();

        ListModel model = ListModel.getInstance();


        firestoreManager = new FirestoreManager();
        Set<User> users = firestoreManager.loadUsers();
        UserSet.getInstance().setUsers(users);


        List<Location> locations = firestoreManager.loadLocations();
        model.setLocations(locations);

        List<Donation> donations = firestoreManager.loadDonations();
        model.setDonations(donations);

    }
}
