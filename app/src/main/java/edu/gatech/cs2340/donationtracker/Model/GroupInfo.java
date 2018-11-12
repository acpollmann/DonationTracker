package edu.gatech.cs2340.donationtracker.Model;
import java.util.ArrayList;

/**
 * Implementation that will initiate group of the expandable list
 * view on the View Location page, shows the different items to
 * sort by for the locations
 *
 * @author Group 71B
 * @version 1.0
 */
public class GroupInfo {

    private String name;
    private ArrayList<ChildInfo> list = new ArrayList<>();

    /**
     * Gets the name from the child information on the view location activity
     * @return name of the child info
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name with the list of sort name from view location activity
     * @param name a string that is a name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * gets the product list with the list of sort names from view location activity
     * @return Array list of a string that is a list of sort names
     */
    public ArrayList<ChildInfo> getProductList() {
        return list;
    }

    /**
     * Sets the product list with the list of sort names from view location activity
     * @param productList a string that is a list of sort names
     */
    public void setProductList(ArrayList<ChildInfo> productList) {
        this.list = productList;
    }

}
