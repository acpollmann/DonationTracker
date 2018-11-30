package edu.gatech.cs2340.donationtracker;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import edu.gatech.cs2340.donationtracker.Controller.RegisterActivity;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserModel;

import static android.support.test.espresso.Espresso.onData;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AllOf.allOf;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    private final User user1 = new User("email1", "password1");

    @Rule
    public ActivityTestRule<RegisterActivity> mActivityRule =
            new ActivityTestRule<>(RegisterActivity.class);

    @Test
    public void testOnRegisterPressed_EmptyFields() {
        // click the register button, leaving fields empty
        onView(withId(R.id.saveRegistrationButton)).perform(click());

        // check that fields become empty
        onView(withId(R.id.email_field)).check(matches(withText("")));
        onView(withId(R.id.password_field)).check(matches(withText("")));

        // check that the error message "Email and password are required." is displayed
        onView(withId(R.id.error_message_register))
                .check(matches(withText(R.string.registerErrorM)));
    }

    @Test
    public void testOnRegisterPressed_UserAlreadyExists() {
        UserModel mockUserModel = mock(UserModel.class);
        when(mockUserModel.userExists(user1.getEmail())).thenReturn(true);
        mActivityRule.getActivity().setUserModel(mockUserModel);

        // type email1 into the email field
        onView(withId(R.id.email_field)).perform(typeText(user1.getEmail()), closeSoftKeyboard());

        // type password1 into the password field
        onView(withId(R.id.password_field)).perform(typeText(user1.getPassword()),
                closeSoftKeyboard());

        // click User in the spinner
        onView(withId(R.id.user_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("User"))).perform(click());

        // click the register button
        onView(withId(R.id.saveRegistrationButton)).perform(click());

        // check that fields become empty
        onView(withId(R.id.email_field)).check(matches(withText("")));
        onView(withId(R.id.password_field)).check(matches(withText("")));

        // check that the error message "User already exists." is displayed
        onView(withId(R.id.error_message_register))
                .check(matches(withText(R.string.registerExistingUserErrorM)));
    }

    @Test
    public void testOnRegisterPressed_SuccessfulRegistration() {
        UserModel mockUserModel = mock(UserModel.class);
        mActivityRule.getActivity().setUserModel(mockUserModel);

        // type newEmail into the email field
        onView(withId(R.id.email_field)).perform(typeText("newEmail"), closeSoftKeyboard());

        // type newPassword into the password field
        onView(withId(R.id.password_field)).perform(typeText("newPassword"), closeSoftKeyboard());

        // click User in the spinner
        onView(withId(R.id.user_spinner)).perform(click());
        onData(allOf(is(instanceOf(String.class)), is("User"))).perform(click());

        // click the register button
        onView(withId(R.id.saveRegistrationButton)).perform(click());

        verify(mockUserModel).addUser("newEmail", "newPassword", "User");
    }
}
