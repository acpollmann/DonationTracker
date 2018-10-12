package edu.gatech.cs2340.donationtracker.Model;

import android.location.Location;
import android.media.Image;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;
/**
 * Created by amypollmann on 10/11/18.
 */

public class Donation {
    /** allow user to assign number to specific donations */
    private static int Next_key = 0;
    private int key;
    private Timestamp timeStamp;
    private LocationItem location;
    private String shortDescription;
    private String longDescription;
    /** donation's value in dollars */
    private int value;
    private String category;
    private List<String> comments;
    private Image picture;

    public Donation(int key, Timestamp timestamp, LocationItem location,
                    String shortDescription, String longDescription,
                    int value, String category) {
        this.key = key;
        this.timeStamp = new Timestamp(new Date().getTime());
        this.location = location;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.value = value;
        this.category = category;
        this.comments = new ArrayList<String>();
        this.picture = null;
    }


    public int getKey() {return key;}
    public Timestamp getTimeStamp() {return timeStamp;}
    public LocationItem getLocation() {return location;}
    public String getShortDescription() {return shortDescription;}
    public String getLongDescription() {return longDescription;}
    public int getValue() {return value;}
    public String getCategory() {return category;}
    public List<String> getComments() {return comments;}
    public Image getPicture() {return picture;}

}
