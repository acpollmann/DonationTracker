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

import java.util.HashMap;
import java.util.HashSet;
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

    public FirestoreManager() {
        db = FirebaseFirestore.getInstance();
        users = new HashSet<>();
    }

    /** Stores a User to Firestore. */
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

}
