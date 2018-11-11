package edu.gatech.cs2340.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.cs2340.donationtracker.Controller.LoginActivity;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    private final User user1 = new User("email1", "password1");

    @Rule
    public ActivityTestRule<LoginActivity> mActivityRule =
            new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void testOnLoginPressed_ValidUser() {
        UserSet mockUserSet = mock(UserSet.class);
        when(mockUserSet.validUser(user1.getEmail(), user1.getPassword())).thenReturn(true);
        mActivityRule.getActivity().setUserSet(mockUserSet);

        // type email1 into the email field
        onView(withId(R.id.email_field)).perform(typeText(user1.getEmail()), closeSoftKeyboard());

        // type password1 into the password field
        onView(withId(R.id.password_field)).perform(typeText(user1.getPassword()), closeSoftKeyboard());

        // click the login button
        onView(withId(R.id.email_sign_in_button)).perform(click());
    }

    @Test
    public void testOnLoginPressed_InvalidUser() {
        UserSet mockUserSet = mock(UserSet.class);
        mActivityRule.getActivity().setUserSet(mockUserSet);

        // type Invalid email into the email field
        onView(withId(R.id.email_field)).perform(typeText("Invalid email"), closeSoftKeyboard());

        // type password1 into the password field
        onView(withId(R.id.password_field)).perform(typeText("Invalid password"), closeSoftKeyboard());

        // click the login button
        onView(withId(R.id.email_sign_in_button)).perform(click());

        // check that fields become empty
        onView(withId(R.id.email_field)).check(matches(withText("")));
        onView(withId(R.id.password_field)).check(matches(withText("")));

        // check that the error message "Invalid email/password" is displayed
        onView(withId(R.id.error_message))
                .check(matches(withText(R.string.loginErrorM)));
    }
}
