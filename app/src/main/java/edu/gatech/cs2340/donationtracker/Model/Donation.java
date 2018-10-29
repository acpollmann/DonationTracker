package edu.gatech.cs2340.donationtracker.Model;

import android.media.Image;

import java.util.Arrays;
import java.util.List;

/**
 * Created by amypollmann on 10/11/18.
 */

public class Donation {
    /** allow user to assign number to specific donations */
    private static int Next_key = 0;
    private int key;
    private String name;
    private String timeStamp;
    private LocationItem location;
    private String shortDescription;
    private String fullDescription;
    /** donation's value in dollars */
    private String value;
    private String category;
    private String comments;
    private Image picture;

    public static List<String> legalCategories = Arrays.asList("Clothing", "Hat",
            "Kitchen", "Electronics", "Household", "Other");

    public static List<String> searchLegalCategories = Arrays.asList("All", "Clothing", "Hat",
            "Kitchen", "Electronics", "Household", "Other");

    public Donation(String name, LocationItem location, String timeStamp,
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
        this.picture = null;
        this.key = Donation.Next_key++;

    }

    public int getKey() {return key;}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public LocationItem getLocation() {
        return location;
    }

    public void setLocation(LocationItem location) {
        this.location = location;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public Image getPicture() {
        return picture;
    }

    public void setPicture(Image picture) {
        this.picture = picture;
    }

}
