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
        HashMap<String, String> listOfUsers = new HashMap<>();
        listOfUsers.put(user.getUsername(), user.getPassword());

    }

}
