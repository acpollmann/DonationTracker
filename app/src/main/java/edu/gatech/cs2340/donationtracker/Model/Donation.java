package edu.gatech.cs2340.donationtracker.Model;

import android.media.Image;

import java.util.Arrays;
import java.util.List;

/**
 * User creates the donation using the different requirements.
 * User is later able to access the donation object formed here.
 * Created on 10/11/18.
 * @author Group 71B
 * @version 1.0
 */
public class Donation {
    /** allow user to assign number to specific donations */
    private static int next_key;
    private final int key;
    private String name;
    private String timeStamp;
    private Location location;
    private String shortDescription;
    private String fullDescription;
    /** donation's value in dollars */
    private String value;
    private String category;
    private String comments;
    private Image picture;
    public static final List<String> legalCategories = Arrays.asList("Clothing", "Hat",
            "Kitchen", "Electronics", "Household", "Other");

    public static final List<String> searchLegalCategories = Arrays.asList("All", "Clothing", "Hat",
            "Kitchen", "Electronics", "Household", "Other");

    /**
     * Adds a donation to the donation list for application
     * @param location what required of a donation
     * @param timeStamp what required of a donation
     * @param shortDescription what required of a donation
     * @param fullDescription what required of a donation
     * @param value what required of a donation
     * @param category what required of a donation
     * @param comments what required of a donation
     * @param name what required of a donation
     */
    public Donation(String name, Location location, String timeStamp,
                    String shortDescription, String fullDescription,
                    String value, String category, String comments) {
        this.name = name;
        this.timeStamp = timeStamp;
        this.location = location;
        this.shortDescription = shortDescription;
        this.fullDescription = fullDescription;
        this.value = value;
        this.category = category;
        this.comments = comments;
        this.key = getNext_Key();

    }

    /**
     * Gets the key from donation form for the activity pages
     * @return key of the donation item
     */
    public int getKey() {return key;}

    /**
     * Gets next key from list model donation list for application
     * @return key for next donation
     */
    private int getNext_Key() {
        return ListModel.getInstance().getDonationListSize() + 1;
    }


    /**
     * Gets the name from donation form for the activity pages
     * @return name of the donation
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name from donation form for the activity pages
     * @param name a string that is a list of sort names
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the location from donation form for the activity pages
     * @return timestamp of the donation
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * Sets the timestamp from donation form for the activity pages
     * @param timeStamp a string that is a list of sort names
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * Gets the location from donation form for the activity pages
     * @return location of the donation
     */
    public Location getLocation() {
        return location;
    }

    /**
     * Sets the location from donation form for the activity pages
     * @param location a string that is a list of sort names
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     * Gets the short description from donation form for the activity pages
     * @return short description of the donation
     */
    public String getShortDescription() {
        return shortDescription;
    }

    /**
     * Sets the short description from donation form for the activity pages
     * @param shortDescription a string that is a list of sort names
     */
    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    /**
     * Gets the full description from donation form for the activity pages
     * @return full description of the donation
     */
    public String getFullDescription() {
        return fullDescription;
    }

    /**
     * Sets the full description from donation form for the activity pages
     * @param fullDescription a string that is a list of sort names
     */
    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    /**
     * Gets the value from donation form for the activity pages
     * @return value of the donation
     */
    public String getValue() {
        return value;
    }

    /**
     * Sets the value from donation form for the activity pages
     * @param value a string that is a list of sort names
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * Gets the category from donation form for the activity pages
     * @return category of the donation
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the category from donation form for the activity pages
     * @param category a string that is a list of sort names
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * Gets the comments from donation form for the activity pages
     * @return comment of the donation
     */
    public String getComments() {
        return comments;
    }

    /**
     * Sets the comments from donation form for the activity pages
     * @param comments a string that is a list of sort names
     */
    public void setComments(String comments) {
        this.comments = comments;
    }

    /**
     * Gets the picture from donation form for the activity pages
     * @return picture of the donation
     */
    public Image getPicture() {
        return picture;
    }

    /**
     * Sets the picture from donation form for the activity pages
     * @param picture a string that is a list of sort names
     */
    public void setPicture(Image picture) {
        this.picture = picture;
    }

}