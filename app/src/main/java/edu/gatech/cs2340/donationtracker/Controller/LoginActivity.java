package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Map;
import java.util.Set;

import edu.gatech.cs2340.donationtracker.Model.UserMap;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Activity which represents a login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mUsernameField = findViewById(R.id.name_field);
        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);
    }

    public void onLoginPressed(View view) {
        String username = mUsernameField.getText().toString();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mErrorMessage.setText("");

        Map<String, String> findByEmail = UserMap.usersByEmail(username, email, password);
        Map<String, String> findByName = UserMap.usersByName(username, email, password);
        Set<String> emailSet = findByEmail.keySet();
        Set<String> nameSet = findByName.keySet();
        Set<String> passwordEmailSet = (Set<String>) findByEmail.values();
        Set<String> passwordNameSet = (Set<String>) findByName.values();

        if(emailSet.contains(email) && passwordEmailSet.contains(password)) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
        else if (nameSet.contains(username) && passwordNameSet.contains(password)) {
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
