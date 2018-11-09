package edu.gatech.cs2340.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import java.util.Set;

import edu.gatech.cs2340.donationtracker.Controller.RegisterActivity;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    /** this line is preferred way to hook up to activity */
    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Mock private User mockUser;
    private RegisterActivity registerActivity = spy(new RegisterActivity());

    @Test
    public void testOnRegisterPressed_typeUser() {
        // type "hello@example.com" in the email field
        onView(withId(R.id.email_field)).perform(typeText("hello@example.com"), closeSoftKeyboard());
        // type "password" in the password field
        onView(withId(R.id.password_field)).perform(typeText("password"), closeSoftKeyboard());
        // click on the spinner to select it
        onView(withId(R.id.user_spinner)).perform(click());
        // now access the adapter to look for the chosen item ("User" in this case) and select it
        onData(allOf(is(instanceOf(String.class)), is("User"))).perform(click());
        // now click the register button
        onView(withId(R.id.saveRegistrationButton)).perform(click());

        doReturn(mockUser).when(registerActivity).makeUser()
        User expectedUser = new User("password", "hello@example.com");
    }

//    @After
//    public void reset() {
//        onView(withId(R.id.register_button)).perform(click());
//    }
}
