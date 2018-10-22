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

public class UserSet {
    /** Singleton instance */
    private static final UserSet _instance = new UserSet(new FirestoreManager());
    public static UserSet getInstance() { return _instance; }

    /** Set of users known to the application. */
    private Set<User> _users;

    /** The FirestoreManager responsible for saving Users to and loading Users from Firestore. */
    private FirestoreManager firestoreManager;

    /**
     * make a new model
     */
    private UserSet(FirestoreManager firestoreManager) {
        _users = new HashSet<>();
        this.firestoreManager = firestoreManager;
    }

    /**
     * @return the set of users stored in our app
     */
    public Set<User> getUsers() { return _users; }

    /**
     * This method is only called once on startup, when the saved users from Firestore is loaded
     * into the application.
     *
     * @param users the set of users stored in Firestore
     */
    public void setUsers(Set<User> users) {
        _users = users;
    }

    /**
     * add a user to the app.  checks if the user is already entered
     *
     * uses O(n) linear search for user
     *
     * @param user  the user to be added
     * @return true if added, false if a duplicate
     */
    public boolean addUser(User user) {
       if (_users.contains(user)) {
           return false;
       }
       _users.add(user);
       firestoreManager.add(user);
       return true;
    }
}
