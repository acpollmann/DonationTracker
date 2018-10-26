package edu.gatech.cs2340.donationtracker.Model;

public class LocationItem {
    /** allow user to assign number to specific locations */
    private static int Next_key = 0;

    /** this locations key */
    private int key;

    /** this location name */
    private String locationName;

    /** this locations latitude */
    private double latitude;

    /** this locations longitude */
    private double longitude;

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


    public LocationItem(int key, String locationName, double latitude, double longitude,
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

    public int getKey() {return key;}
    public String getLocationName() {return locationName;}
//    public void setLocationName(String name) {
//        this.locationName = name;
//    }
    public double getLatitude() {return latitude;}
    public double getLongitude() {return longitude;}
    public String getAddress() {return locationAddress;}
    public String getCity() {return locationCity;}
    public String getState() {return locationState;}
    public int getZipCode() {return zipCode;}
    public String getType() {return locationType;}
    public String getPhone() {return phoneNumber;}
    public String getWebsite() {return website;}

    @Override
    public String toString() {
        return locationName;
    }

}
