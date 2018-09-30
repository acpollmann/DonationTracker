package edu.gatech.cs2340.donationtracker.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Activity which represents a login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);
    }

    public void onLoginPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mErrorMessage.setText("");

        if (email.equals("user") && password.equals("password")) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText("Invalid email/password");
        }
    }
    public void onCancelPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
    }
}
