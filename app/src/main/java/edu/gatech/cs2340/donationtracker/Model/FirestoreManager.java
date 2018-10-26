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
    private FirebaseFirestore db;

    private Set<User> users;

    private List<Donation> donations;

    private List<LocationItem> locations;

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        users = new HashSet<>();
        locations = new ArrayList<>();
        donations = new ArrayList<>();
    }

    /** Stores a User to Firestore.
     * @param user*/
    public void add(User user) {
//        Map<String, Object> userDoc = new HashMap<>();
//        userDoc.put("email", user.getEmail());
//        userDoc.put("password", user.getPassword());
//        userDoc.put("id", user.getId());

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
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
                            User user = new User((String) userDoc.get("password"), (String) userDoc.get("email"));
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
    public void addLocation(LocationItem location) {

        // Add a new document with a generated ID
        db.collection("locations")
                .add(location)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
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
    public List<LocationItem> loadLocations() {

        db.collection("locations")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d("doc", document.getId() + " => " + document.getData());

                                Map<String, Object> userDoc = document.getData();

                                long keyl = (long) userDoc.get("key");
                                int key  = (int) keyl;

//                                long latl = (long) userDoc.get("latitude");
//                                double latitude = (double) latl;
//
//                                long longl = (long) userDoc.get("longitude");
//                                double longitude = (double) longl;
                                long zipCodel = (long) userDoc.get("zipCode");
                                int zipCode  = (int) zipCodel;

                                LocationItem locationItem =
                                        new LocationItem(key,
                                                (String) userDoc.get("locationName"),
                                                (double) userDoc.get("latitude"),
                                                (double) userDoc.get("latitude"),
                                                (String) userDoc.get("locationAddress"),
                                                (String) userDoc.get("locationCity"),
                                                (String) userDoc.get("locationState"),
                                                zipCode,
                                                (String) userDoc.get("locationType"),
                                                (String) userDoc.get("phoneNumber"),
                                                (String) userDoc.get("website")
                                        );
//                                int key, String _locationName, double latitude, double longitude,
//                                String _locationAddress, String _locationCity, String _locationState,
//                                int _zipCode, String _locationType, String _phoneNumber, String _website

                                locations.add(locationItem);
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
                        Log.d("SUCCESS", "DocumentSnapshot added with ID: " + documentReference.getId());
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

                                Map<String, Object> userDoc = document.getData();

                                Map<String, Object> locationMap =
                                        (Map<String, Object>) userDoc.get("location");

                                long keyl = (long) userDoc.get("key");
                                int key  = (int) keyl;

                                long latl = (long) userDoc.get("latitude");
                                double latitude = (double) latl;

                                long longl = (long) userDoc.get("longitude");
                                double longitude = (double) longl;


                                LocationItem location = new LocationItem(
                                        key,
                                        (String) locationMap.get("locationName"),
                                        latitude,
                                        longitude,
                                        (String) locationMap.get("locationAddress"),
                                        (String) locationMap.get("locationCity"),
                                        (String) locationMap.get("locationState"),
                                        (int) locationMap.get("zipCode"),
                                        (String) locationMap.get("locationType"),
                                        (String) locationMap.get("phoneNumber"),
                                        (String) locationMap.get("website")
                                );

//                                int key, String locationName, double latitude, double longitude,
//                                String locationAddress, String locationCity, String locationState,
//                                int zipCode, String locationType, String phoneNumber, String website

                                        Donation newDonation =
                                        new Donation((String) userDoc.get("name"),
                                                location,
                                                (String) userDoc.get("timeStamp"),
                                                (String) userDoc.get("shortDescription"),
                                                (String) userDoc.get("fullDescription"),
                                                (String) userDoc.get("value"),
                                                (String) userDoc.get("category"),
                                                (String) userDoc.get("comments")
                                        );


//                                String name, LocationItem location, String timeStamp,
//                                        String shortDescription, String fullDescription,
//                                        String value, String category, String comments
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
