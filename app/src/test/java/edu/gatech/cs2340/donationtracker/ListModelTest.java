package edu.gatech.cs2340.donationtracker;

import org.junit.Before;
import org.junit.Test;

import edu.gatech.cs2340.donationtracker.Model.FirestoreManager;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.Location;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;

public class ListModelTest {
    private ListModel testListModel;

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

    private final Location location2 = new Location(
            2,
            "location2",
            2.0,
            2.0,
            "address2",
            "city2",
            "state2",
            20000,
            "locationType2",
            "phoneNumber2",
            "website2"
    );

    private final Location location3 = new Location(
            3,
            "location3",
            3.0,
            3.0,
            "address3",
            "city3",
            "state3",
            30000,
            "locationType3",
            "phoneNumber3",
            "website3"
    );

    @Before
    public void setup() {
        testListModel = ListModel.getTestInstance(mock(FirestoreManager.class));
    }

    @Test
    public void testLocationById_EmptyList() {
        assertTrue(testListModel.getLocations().isEmpty());
        assertNull(testListModel.findLocationById(-1));
    }

    @Test
    public void testLocationById_OneItem_KeyFound() {
        testListModel.addLocation(location1);
        assertFalse(testListModel.getLocations().isEmpty());
        assertEquals(location1, testListModel.findLocationById(1));
    }

    @Test
    public void testLocationById_OneItem_KeyNotFound() {
        testListModel.addLocation(location1);
        assertFalse(testListModel.getLocations().isEmpty());
        assertNull(testListModel.findLocationById(-1));
    }

    @Test
    public void testLocationById_LargerList_KeyFound() {
        testListModel.addLocation(location1);
        testListModel.addLocation(location2);
        testListModel.addLocation(location3);
        assertFalse(testListModel.getLocations().isEmpty());
        assertEquals(location1, testListModel.findLocationById(1));
        assertEquals(location2, testListModel.findLocationById(2));
        assertEquals(location3, testListModel.findLocationById(3));
    }

    @Test
    public void testLocationById_LargerList_KeyNotFound() {
        testListModel.addLocation(location1);
        testListModel.addLocation(location2);
        testListModel.addLocation(location3);
        assertFalse(testListModel.getLocations().isEmpty());
        assertNull(testListModel.findLocationById(-1));
    }
}
