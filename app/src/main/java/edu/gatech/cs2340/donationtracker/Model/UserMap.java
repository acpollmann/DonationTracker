package edu.gatech.cs2340.donationtracker.Model;

import java.util.HashMap;
import java.util.Map;

/*
* Class UserMap for storing data of various types of users
* Created by Aviva Smith 9/30/18
* */

public class UserMap {

    public static void main(String[] args) {
        String username = null;
        String password = null;
        String email = null;
        User user = new User(username, password, email);

        /*Hashmap stores users by email and password key-value pairs so
        * system can check if user is in database.*/
        HashMap<String, String> UsersByEmail = new HashMap<>();
        UsersByEmail.put(user.getEmail(), user.getPassword());
    }

}
