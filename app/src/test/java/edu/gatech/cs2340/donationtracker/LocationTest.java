package edu.gatech.cs2340.donationtracker;

import org.junit.Test;

import edu.gatech.cs2340.donationtracker.Model.Location;

import static org.junit.Assert.assertEquals;

public class LocationTest {

    @Test
    public void testCreateWithKey() {
        int key = 1;
        String locationName = "Test Location";
        double latitude = 100.0;
        double longitude = 100.0;
        String locationAddress = "123 Main St";
        String locationCity = "Atlanta";
        String locationState = "GA";
        int zipCode = 30000;
        String locationType = "Warehouse";
        String phoneNumber = "404-555-5555";
        String website = "test.com";

        Location location = new Location(key, locationName, latitude, longitude, locationAddress,
                locationCity, locationState, zipCode, locationType, phoneNumber, website);

        assertEquals(key, location.getKey());
        assertEquals(locationName, location.getLocationName());
        assertEquals(latitude, location.getLatitude(), 1 * Math.pow(10, -9));
        assertEquals(longitude, location.getLongitude(), 1 * Math.pow(10, -9));
        assertEquals(locationAddress, location.getAddress());
        assertEquals(locationCity, location.getCity());
        assertEquals(locationState, location.getState());
        assertEquals(zipCode, location.getZipCode());
        assertEquals(locationType, location.getType());
        assertEquals(phoneNumber, location.getPhone());
        assertEquals(website, location.getWebsite());
    }

    @Test
    public void testCreateWithoutKey() {
        String locationName = "Test Location";
        double latitude = 100.0;
        double longitude = 100.0;
        String locationAddress = "123 Main St";
        String locationCity = "Atlanta";
        String locationState = "GA";
        int zipCode = 30000;
        String locationType = "Warehouse";
        String phoneNumber = "404-555-5555";
        String website = "test.com";

        Location location = new Location(locationName, latitude, longitude, locationAddress,
                locationCity, locationState, zipCode, locationType, phoneNumber, website);

        assertEquals(1, location.getKey());
        assertEquals(locationName, location.getLocationName());
        assertEquals(latitude, location.getLatitude(), 1 * Math.pow(10, -9));
        assertEquals(longitude, location.getLongitude(), 1 * Math.pow(10, -9));
        assertEquals(locationAddress, location.getAddress());
        assertEquals(locationCity, location.getCity());
        assertEquals(locationState, location.getState());
        assertEquals(zipCode, location.getZipCode());
        assertEquals(locationType, location.getType());
        assertEquals(phoneNumber, location.getPhone());
        assertEquals(website, location.getWebsite());
    }
}
