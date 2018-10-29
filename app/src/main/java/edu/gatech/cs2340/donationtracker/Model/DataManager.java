package edu.gatech.cs2340.donationtracker.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by robertwaters on 3/13/18.
 *
 * Structurer
 *
 * primary responsibility is to manage a group of DataElements
 */

class DataManager {
    List<DataElement> theData;

    DataManager() {
        theData = new ArrayList<>();
        makeSomeData();
    }

    private void makeSomeData() {
            addReport(new DataElement("Coke Zero", "Sam's Deli", new LocationItem(33.749, -84.388)));
            addReport(new DataElement("Pepsi", "Grandma Garage", new LocationItem(33.8, -84.5)));
    }

    void addReport(DataElement de) {
        theData.add(de);
    }


    List<DataElement> getData() { return theData; }



}
