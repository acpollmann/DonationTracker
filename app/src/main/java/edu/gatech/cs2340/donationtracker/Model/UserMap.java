package edu.gatech.cs2340.donationtracker.Model;

import java.util.HashMap;
import java.util.Map;

import edu.gatech.cs2340.donationtracker.Controller.RegisterActivity;

/*
* Class UserMap for storing data of various types of users
* Created by Aviva Smith 9/30/18
* */

public class UserMap {



    public static Map<String, String> usersByName(String username, String email, String password) {
        HashMap<String, String> UsersByName = new HashMap<>();
        UsersByName.put(username, password);
        return UsersByName;
    }
}
