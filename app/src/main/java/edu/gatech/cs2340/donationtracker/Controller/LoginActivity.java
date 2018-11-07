package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;
import edu.gatech.cs2340.donationtracker.R;


/**
 * Activity which represents a login screen that offers login via email/password.
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

        userSet = UserSet.getInstance();
        configureBackButton();
    }

    public void onLoginPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mErrorMessage.setText("");

        User user = new User(password, email);

        boolean found = false;
        for (User u : userSet.getUsers()) {
            if (u.equals(user)) {
                found = true;
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        }

        if (!found) {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText("Invalid email/password");
        }
    }
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
