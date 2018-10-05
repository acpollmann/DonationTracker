package edu.gatech.cs2340.donationtracker.Model;

public class LocationItem {
    /** allow user to assign number to specific locations */
    private static int Next_key = 0;

    /** this locations key */
    private int key;

    /** this location name */
    private String _locationName;

    /** this locations latitude */
    private double latitude;

    /** this locations longitude */
    private double longitude;

    /** this location address */
    private String _locationAddress;

    /** this location city */
    private String _locationCity;

    /** this location state */
    private String _locationState;

    /** this location zipCode*/
    private int _zipCode;

    /** this location type*/
    private String _locationType;

    /** this location phoneNumber*/
    private String _phoneNumber;

    /** this location website*/
    private String _website;


    public LocationItem(int key, String _locationName, double latitude, double longitude,
                        String _locationAddress, String _locationCity, String _locationState,
                        int _zipCode, String _locationType, String _phoneNumber, String _website) {
        this.key = key;
        this._locationName = _locationName;
        this.latitude = latitude;
        this.longitude = longitude;
        this._locationAddress = _locationAddress;
        this._locationCity = _locationCity;
        this._locationState = _locationState;
        this._zipCode = _zipCode;
        this._locationType = _locationType;
        this._phoneNumber = _phoneNumber;
        this._website = _website;
    }

    public int getKey() {return key;}
    public String _getLocationName() {return _locationName;}
    public double _getLatitude() {return latitude;}
    public double _getLongitude() {return longitude;}
    public String _getAddress() {return _locationAddress;}
    public String _getCity() {return _locationCity;}
    public String _getState() {return _locationState;}
    public int _getZipCode() {return _zipCode;}
    public String _getType() {return _locationType;}
    public String _getPhone() {return _phoneNumber;}
    public String _getWebsite() {return _website;}



}
