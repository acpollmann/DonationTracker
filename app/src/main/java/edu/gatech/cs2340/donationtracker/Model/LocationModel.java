package edu.gatech.cs2340.donationtracker.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for the list of locations known to the app
 *
 * @author Group 71B
 * @version 1.0
 */
public class LocationModel {

    private static LocationModel instance;

    /**
     * Gets an instance of LocationModel. If instance hasn't been initialized yet, a new instance of
     * LocationModel is created.
     * @return database instance
     */
    public static synchronized LocationModel getInstance() {
        if (instance == null) {
            instance = new LocationModel(FirestoreManager.getInstance());
        }
        return instance;
    }

    /**
     * Gets an instance of LocationModel, used for testing purposes
     * @param firestoreManager an instance of FirestoreManager
     * @return an instance of LocationModel
     */
    public static LocationModel getTestInstance(FirestoreManager firestoreManager) {
        return new LocationModel(firestoreManager);
    }

    private List<Location> locations;

    /** The FirestoreManager responsible for saving Locations to and loading Locations
     * from Firestore. */
    private final FirestoreManager firestoreManager;

    private LocationModel(FirestoreManager firestoreManager) {
        locations = new ArrayList<>();
        this.firestoreManager = firestoreManager;
    }

    /**
     * Adds a location to the location list for application
     * @param latitude what required of a location
     * @param longitude what required of a location
     * @param street what required of a location
     * @param city what required of a location
     * @param state what required of a location
     * @param zip what required of a location
     * @param type what required of a location
     * @param phone what required of a location
     * @param web what required of a location
     * @param name what required of a location
     */
    public void addLocation(String name, String latitude, String longitude,
                            String street, String city, String state, String zip, String type,
                            String phone, String web) {
        Location newLocation = new Location(name, Double.parseDouble(latitude),
                Double.parseDouble(longitude), street, city, state, Integer.parseInt(zip),
                type, phone, web);
        locations.add(newLocation);
        firestoreManager.addLocation(newLocation);
    }

    /**
     * Adds a location to the location list for application
     * @param location the location to add
     */
    public void addLocation(Location location) {
        locations.add(location);
        firestoreManager.addLocation(location);
    }

    /**
     * Gets the list of locations for the the application
     * @return list of locations
     */
    public List<Location> getLocations() {
        return locations;
    }

    /**
     * Sets the location list for application
     * @param locations list of locations
     */
    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    /**
     * Gets the size of location list for application
     * @return size of list of locations
     */
    int getLocationListSize() {
        return locations.size();
    }

    /**
     * finds item by the key value
     * @param key id for item
     * @return location from id
     */
    public Location findLocationById(int key) {

        for (Location l : locations) {
            if (l.getKey() == key) {
                return l;
            }
        }

        return null;
    }
}
