package edu.gatech.cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.DonationModel;
import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.Location;

import static org.junit.Assert.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class DonationModelTest {
    private DonationModel testDonationModel;
    private FirestoreManager mockFirestoreManager;

    private final Location location1 = new Location(
            1,
            "location1",
            1.0,
            1.0,
            "address1",
            "city1",
            "state1",
            10000,
            "locationType1",
            "phoneNumber1",
            "website1"
    );

    private final Donation donation1 = new Donation(
            "donation1",
            location1,
            "timeStamp1",
            "shortDescription1",
            "fullDescription1",
            "value1",
            "category1",
            "comments1"
    );

    @Before
    public void setup() {
        mockFirestoreManager = mock(FirestoreManager.class);
        testDonationModel = DonationModel.getTestInstance(mockFirestoreManager);
    }

    @Test
    public void testAddDonation() {
        testDonationModel.addDonation(donation1.getName(), donation1.getLocation(),
                donation1.getTimeStamp(), donation1.getShortDescription(),
                donation1.getFullDescription(), donation1.getValue(), donation1.getCategory(),
                donation1.getComments());
        assertFalse(testDonationModel.getDonations().isEmpty());

        verify(mockFirestoreManager).addDonation(donation1);
    }
}
