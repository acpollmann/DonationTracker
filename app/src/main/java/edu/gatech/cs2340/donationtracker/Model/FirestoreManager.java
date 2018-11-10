package edu.gatech.cs2340.donationtracker.Model;

import android.support.annotation.NonNull;
import android.util.Log;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * This class acts as an interface between the application and Firestore. The model gets passed into
 * this class, where objects will be converted into documents to store in Firestore.
 */
public class FirestoreManager {

    /** Provides direct access to the Firestore database. */
    private final FirebaseFirestore db;

    private final Set<User> users;

    private final List<Donation> donations;

    private final List<Location> locations;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        users = new HashSet<>();
        locations = new ArrayList<>();
        donations = new ArrayList<>();
    }

    /** Stores a User to Firestore.
     * @param user*/
    public void add(User user) {

        // Add a new document with a generated ID
        db.collection("users")
                .add(user)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FAILURE", "Error adding document", e);
                    }
                });
    }

    /**
     * @return a set of Users stored in Firestore
     */
    public Set<User> loadUsers() {


        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("doc", document.getId() + " => " + document.getData());

                                Map<String, Object> userDoc = document.getData();
                                User user = new User(
                                    (String) userDoc.get("email"), (String) userDoc.get("password")
                                );
                                users.add(user);
                            }
                        } else {
                            Log.d("docError", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return users;
    }

    /** Stores a Location to Firestore.
     * @param location*/
    public void addLocation(Location location) {

        // Add a new document with a generated ID
        db.collection("locations")
                .add(location)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FAILURE", "Error adding document", e);
                    }
                });
    }

    /**
     * @return a set of Locations stored in Firestore
     */
    public List<Location> loadLocations() {

        db.collection("locations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("doc", document.getId() + " => " + document.getData());

                                Map<String, Object> locationMap = document.getData();


                                long keyl = (long) locationMap.get("key");
                                int key  = (int) keyl;

                                double latitude = (double) locationMap.get("latitude");

                                double longitude = (double) locationMap.get("longitude");

                                long zipl = (long) locationMap.get("zipCode");
                                int zipCode  = (int) zipl;

                                Location location = new Location(
                                        key,
                                        (String) locationMap.get("locationName"),
                                        latitude,
                                        longitude,
                                        (String) locationMap.get("address"),
                                        (String) locationMap.get("city"),
                                        (String) locationMap.get("state"),
                                        zipCode,
                                        (String) locationMap.get("type"),
                                        (String) locationMap.get("phone"),
                                        (String) locationMap.get("website")
                                );

                                locations.add(location);
                            }
                        } else {
                            Log.d("docError", "Error getting documents: ", task.getException());
                        }

                    }
                });

        return locations;
    }


    /** Stores a Donation to Firestore.
     * @param donation*/
    public void addDonation(Donation donation) {

        // Add a new document with a generated ID
        db.collection("donations")
                .add(donation)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: "
                                + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("FAILURE", "Error adding document", e);
                    }
                });
    }

    /**
     * @return a set of Locations stored in Firestore
     */
    public List<Donation> loadDonations() {

        db.collection("donations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("doc", document.getId() + " => " + document.getData());

                                Map<String, Object> donationDoc = document.getData();

                                Map<String, Object> locationMap =
                                        (Map<String, Object>) donationDoc.get("location");

                                long keyl = (long) locationMap.get("key");
                                int key  = (int) keyl;
                                Location location = ListModel.getInstance().findItemById(key);
                                Donation newDonation =
                                        new Donation((String) donationDoc.get("name"),
                                                location,
                                                (String) donationDoc.get("timeStamp"),
                                                (String) donationDoc.get("shortDescription"),
                                                (String) donationDoc.get("fullDescription"),
                                                (String) donationDoc.get("value"),
                                                (String) donationDoc.get("category"),
                                                (String) donationDoc.get("comments")
                                        );
                                donations.add(newDonation);
                            }
                        } else {
                            Log.d("docError", "Error getting documents: ", task.getException());
                        }
                    }
                });

        return donations;
    }

}
