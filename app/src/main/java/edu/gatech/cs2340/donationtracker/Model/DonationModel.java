package edu.gatech.cs2340.donationtracker.Model;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementation for the list of donations known to the app
 *
 * @author Group 71B
 * @version 1.0
 */
public class DonationModel {

    private static DonationModel instance;

    /**
     * Gets an instance of DonationModel. If instance hasn't been initialized yet, a new instance of
     * DonationModel is created.
     * @return database instance
     */
    public static synchronized DonationModel getInstance() {
        if (instance == null) {
            instance = new DonationModel(new FirestoreManager());
        }
        return instance;
    }

    /**
     * Gets an instance of DonationModel, used for testing purposes
     * @param firestoreManager an instance of FirestoreManager
     * @return an instance of DonationModel
     */
    public static DonationModel getTestInstance(FirestoreManager firestoreManager) {
        return new DonationModel(firestoreManager);
    }

    private List<Donation> donations;

    /** The FirestoreManager responsible for saving Donations to and loading Donations
     * from Firestore. */
    private final FirestoreManager firestoreManager;

    private DonationModel(FirestoreManager firestoreManager) {
        donations = new ArrayList<>();
        this.firestoreManager = firestoreManager;
    }

    /**
     * Adds a donation to the donation list for application
     * @param location what required of a donation
     * @param timeStamp what required of a donation
     * @param shortDescription what required of a donation
     * @param fullDescription what required of a donation
     * @param value what required of a donation
     * @param category what required of a donation
     * @param comment what required of a donation
     * @param name what required of a donation
     */
    public void addDonation(String name, Location location, String timeStamp,
                            String shortDescription, String fullDescription, String value,
                            String category, String comment) {
        Donation newDonation = new Donation(name, location, timeStamp, shortDescription,
                fullDescription, value, category, comment);
        donations.add(newDonation);
        firestoreManager.addDonation(newDonation);
    }

    /**
     * Gets the donation list for application
     * @return list of donations
     */
    public List<Donation> getDonations() { return donations; }

    /**
     * Sets the donation list for application
     * @param donations list of donations
     */
    public void setDonations(List<Donation> donations) {
        this.donations = donations;
    }

    /**
     * Gets the size of donation list for application
     * @return size of list of donations
     */
    public int getDonationListSize() {
        return donations.size();
    }
}
