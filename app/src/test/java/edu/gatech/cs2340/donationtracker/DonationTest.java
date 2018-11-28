package edu.gatech.cs2340.donationtracker;

import org.junit.Test;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.Location;

import static org.junit.Assert.assertEquals;

public class DonationTest {

    private final Location location = new Location(
            1,
            "location",
            1.0,
            1.0,
            "address",
            "city",
            "state",
            10000,
            "locationType",
            "phoneNumber",
            "website"
    );

    @Test
    public void testCreate() {
        String name = "Example Donation";
        String timeStamp = "1/1/2000 1:00am";
        String shortDescription = "short";
        String fullDescription = "full";
        String value = "10";
        String category = "Other";
        String comments = "comments";

        Donation donation = new Donation(name, location, timeStamp, shortDescription,
                fullDescription, value, category, comments);

        assertEquals(name, donation.getName());
        assertEquals(location, donation.getLocation());
        assertEquals(timeStamp, donation.getTimeStamp());
        assertEquals(shortDescription, donation.getShortDescription());
        assertEquals(fullDescription, donation.getFullDescription());
        assertEquals(value, donation.getValue());
        assertEquals(category, donation.getCategory());
        assertEquals(comments, donation.getComments());
    }
}
