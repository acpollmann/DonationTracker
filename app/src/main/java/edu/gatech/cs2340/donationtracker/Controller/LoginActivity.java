package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.gatech.cs2340.donationtracker.Model.UserModel;
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
    private int attemptsCounter = 3;

    private UserModel userModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);

        setUserModel(UserModel.getInstance());
        configureBackButton();
    }

    /**
     * Sets the UserModel to be used by this activity
     * @param userModel an instance of UserModel
     */
    public void setUserModel(UserModel userModel) {
        this.userModel = userModel;
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
        Button loginButton = findViewById(R.id.sign_in_button);
        mErrorMessage.setText("");

        if (userModel.validUser(email, password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        } else {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText(R.string.loginErrorM);
            attemptsCounter--;
            if (attemptsCounter == 0) {
                loginButton.setEnabled(false);
                mErrorMessage.setText(R.string.lockout);
            }
        }
    }
    public void facebookClick(View view) {

    }
    public void googleClick(View view) {

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
