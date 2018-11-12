package edu.gatech.cs2340.donationtracker.Model;

import android.support.annotation.NonNull;

/**
 * Implementation a location for the location list and detail page
 *
 * @author Group 71B
 * @version 1.0
 */
public class Location {

    /** this locations key */
    private int key;

    /** this location name */
    private String locationName;

    /** this locations latitude */
    private final double latitude;

    /** this locations longitude */
    private final double longitude;

    /** this location address */
    private String locationAddress;

    /** this location city */
    private String locationCity;

    /** this location state */
    private String locationState;

    /** this location zipCode*/
    private int zipCode;

    /** this location type*/
    private String locationType;

    /** this location phoneNumber*/
    private String phoneNumber;

    /** this location website*/
    private String website;

    /**
     * Creates a new Location
     * @param lat  the latitude
     * @param longit  the longitude
     */
    public Location(double lat, double longit) {
        latitude = lat;
        longitude = longit;
    }

    /**
     * Adds a location to the location list for application with key
     * @param latitude what required of a location
     * @param longitude what required of a location
     * @param locationAddress what required of a location
     * @param locationCity what required of a location
     * @param locationState what required of a location
     * @param zipCode what required of a location
     * @param locationType what required of a location
     * @param phoneNumber what required of a location
     * @param website what required of a location
     * @param locationName what required of a location
     * @param key what required of a location
     */
    public Location(int key, String locationName, double latitude, double longitude,
                    String locationAddress, String locationCity, String locationState,
                    int zipCode, String locationType, String phoneNumber, String website) {
        this.key = key;
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationAddress = locationAddress;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.zipCode = zipCode;
        this.locationType = locationType;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    /**
     * Adds a location to the location list for application without key
     * @param latitude what required of a location
     * @param longitude what required of a location
     * @param locationAddress what required of a location
     * @param locationCity what required of a location
     * @param locationState what required of a location
     * @param zipCode what required of a location
     * @param locationType what required of a location
     * @param phoneNumber what required of a location
     * @param website what required of a location
     * @param locationName what required of a location
     */
    public Location(String locationName, double latitude, double longitude,
                    String locationAddress, String locationCity, String locationState,
                    int zipCode, String locationType, String phoneNumber, String website) {
        this.key = getNext_key();
        this.locationName = locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.locationAddress = locationAddress;
        this.locationCity = locationCity;
        this.locationState = locationState;
        this.zipCode = zipCode;
        this.locationType = locationType;
        this.phoneNumber = phoneNumber;
        this.website = website;
    }

    /**
     * Gets key for location
     * @return key for location
     */
    public int getKey() {return key;}
    /**
     * Gets next key from list model location list for application
     * @return key for next location
     */
    private int getNext_key() {
        return ListModel.getInstance().getLocationListSize() + 1;
    }
    /**
     * Gets location name for the location
     * @return name for location
     */
    public String getLocationName() {return locationName;}
    /**
     * Gets latitude for the location
     * @return latitude for location
     */
    public double getLatitude() {return latitude;}
    /**
     * Gets longitude for the location
     * @return longitude for location
     */
    public double getLongitude() {return longitude;}
    /**
     * Gets address for the location
     * @return address for location
     */
    public String getAddress() {return locationAddress;}
    /**
     * Gets city for the location
     * @return city for location
     */
    public String getCity() {return locationCity;}
    /**
     * Gets state for the location
     * @return state for location
     */
    public String getState() {return locationState;}
    /**
     * Gets zip code for the location
     * @return zip code for location
     */
    public int getZipCode() {return zipCode;}
    /**
     * Gets type of location for the location
     * @return location type for location
     */
    public String getType() {return locationType;}
    /**
     * Gets phone number for the location
     * @return phone number for location
     */
    public String getPhone() {return phoneNumber;}
    /**
     * Gets website for the location
     * @return website for location
     */
    public String getWebsite() {return website;}

    /**
     * Create string for location name
     * @return string for name for location
     */
    @NonNull
    @Override
    public String toString() {
        return locationName;
    }

}
