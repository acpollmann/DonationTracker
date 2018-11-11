package edu.gatech.cs2340.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.cs2340.donationtracker.Controller.AddLocationActivity;
import edu.gatech.cs2340.donationtracker.Model.ListModel;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.scrollTo;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

@RunWith(AndroidJUnit4.class)
public class AddLocationActivityTest {

    @Rule
    public ActivityTestRule<AddLocationActivity> mActivityRule =
            new ActivityTestRule<>(AddLocationActivity.class);

    @Test
    public void testOnAddLocationButtonPressed_EmptyFields() {
        // click the Add a Location button, leaving fields empty
        onView(withId(R.id.addLocationButton)).perform(scrollTo()).perform(click());

        // check that the error message "All fields must be filled in." is displayed
        onView(withId(R.id.error_message))
                .check(matches(withText(R.string.addLocationErrorM)));
    }

    @Test
    public void testOnAddLocationButtonPressed_ValidFields() {
        ListModel mockListModel = mock(ListModel.class);
        mActivityRule.getActivity().setListModel(mockListModel);

        onView(withId(R.id.typeSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("Store"))).perform(click());

        onView(withId(R.id.nameField)).perform(typeText("Test Location"), closeSoftKeyboard());
        onView(withId(R.id.LatField)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.LongField)).perform(typeText("100"), closeSoftKeyboard());
        onView(withId(R.id.addressField)).perform(typeText("123 Main St"), closeSoftKeyboard());
        onView(withId(R.id.CityField)).perform(typeText("Atlanta"), closeSoftKeyboard());

        onView(withId(R.id.StateSpinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("GA"))).perform(click());

        onView(withId(R.id.ZipField)).perform(typeText("30000"), closeSoftKeyboard());
        onView(withId(R.id.websiteField)).perform(typeText("test.com"), closeSoftKeyboard());
        onView(withId(R.id.phoneField)).perform(typeText("404-555-1234"), closeSoftKeyboard());

        // click the Add a Location button, leaving fields empty
        onView(withId(R.id.addLocationButton)).perform(click());

        verify(mockListModel).addLocation(
                "Test Location",
                "100",
                "100",
                "123 Main St",
                "Atlanta",
                "GA",
                "30000",
                "Store",
                "404-555-1234",
                "test.com"
        );
    }
}
