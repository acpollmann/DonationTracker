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

//        readSDFile();

    }

    private void readSDFile() {
        //ListModel model = ListModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d(MainActivity.TAG, line);
                String[] tokens = line.split(",");
                int key = Integer.parseInt(tokens[0]);
                double latitude = Double.parseDouble(tokens[2]);
                double longitude = Double.parseDouble(tokens[3]);
                int zip = Integer.parseInt(tokens[7]);
                if (model.findItemById(key) == null) {
                    model.addItem(new LocationItem(key, tokens[1], latitude, longitude,
                            tokens[4], tokens[5], tokens[6], zip,
                            tokens[8], tokens[9], tokens[10]));
                }
            }
            br.close();
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error reading assets", e);
        }
    }
}
