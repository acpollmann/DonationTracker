package edu.gatech.cs2340.donationtracker.Model;

/**
 * Implementation that will initiate child of the expandable list
 * view on the View Location page, shows the different sorts for
 * the locations
 *
 * @author Group 71B
 * @version 1.0
 */
public class ChildInfo {

    private String sequence = "";
    private String name = "";

    /**
     * Gets the sequence from the child information on the view location activity
     */
    public String getSequence() {
        return sequence;
    }

    /**
     * Sets the sequence with the list of strings from view location activity
     * @param sequence a string that is a list of items
     */
    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    /**
     * Gets the name from the child information on the view location activity
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name with the list of sort name from view location activity
     * @param name a string that is a list of sort names
     */
    public void setName(String name) {
        this.name = name;
    }

}
