package edu.gatech.cs2340.donationtracker.Model;

/**
 * Created by robertwaters on 3/13/18.
 * Information Holder
 * Primary responsibility is to maintain all the data about a single thing
 */

public class DataElement {
    private static int Next_ID = 1000;
    private int _id;
    private String _name;
    private String _description;
    private LocationItem _location;

    /**
     * Create new element
     * @param name   the name of the element
     * @param desc   a textual description
     * @param location  the location of the element
     */
    public DataElement(String name, String desc, LocationItem location) {
        _name = name;
        _description = desc;
        _location = location;
        _id = Next_ID++;
    }

    @Override
    public String toString() {
        return  _name + "\n" + _description;
    }

    /*
     Getters for the data elements
     */
    public String getName() { return _name;}
    public String getDescription() {  return _description; }

    public double getLatitude() { return _location.getLatitude(); }
    public double getLongitude() { return _location.getLongitude(); }

}
