package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.UserSet;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the LOGIN page which
 * represents a login screen that offers login via
 * email/password.
 *
 * @author Group 71B
 * @version 1.0
 */
public class LoginActivity extends AppCompatActivity {


    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mErrorMessage;

    private UserSet userSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);

        setUserSet(UserSet.getInstance());
        configureBackButton();
    }

    /**
     * Sets the UserSet to be used by this activity
     * @param userSet an instance of UserSet
     */
    public void setUserSet(UserSet userSet) {
        this.userSet = userSet;
    }

    /**
     * It uses the information, user input, to check and see
     * if the information is correct and exists. If so, the
     * method changes the current screen. Otherwise, it will
     * return a message stating the invalid credentials were
     * inputted and the current user cannot log in.
     *
     * @param view the current view of the LOGIN page
     */
    public void onLoginPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mErrorMessage.setText("");

        if (userSet.validUser(email, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText(R.string.loginErrorM);
        }
    }

    /**
     * It will change the page on the screen if the
     * back button is pressed.
     */
    private void configureBackButton() {
        Button backButton = findViewById(R.id.cancel_login_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });
    }
}
