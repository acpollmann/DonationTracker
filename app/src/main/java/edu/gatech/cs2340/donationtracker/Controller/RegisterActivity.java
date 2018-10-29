package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.Admin;
import edu.gatech.cs2340.donationtracker.Model.LocationEmployee;
import edu.gatech.cs2340.donationtracker.Model.Manager;
import edu.gatech.cs2340.donationtracker.Model.User;
import edu.gatech.cs2340.donationtracker.Model.UserSet;
import edu.gatech.cs2340.donationtracker.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mEmailField;
    private EditText mPasswordField;
    private Spinner spinner;
    private TextView mErrorMessage;

    private UserSet userSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        spinner = (Spinner) findViewById(R.id.user_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        configureBackButton();

        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message_register);

        userSet = UserSet.getInstance();
    }

    private void configureBackButton() {
        Button backButton = (Button) findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void onRegisterPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String type = spinner.getSelectedItem().toString();
        mErrorMessage.setText("");

        User newUser;

        if (type.equals("User")) {
            newUser = new User(password, email);
        } else if (type.equals("Location Employee")) {
            newUser = new LocationEmployee(password, email);
        } else if (type.equals("Admin")) {
            newUser = new Admin(password, email);
        } else {
            newUser = new Manager(password, email);
        }

        boolean found = false;

        for (User u : userSet.getUsers()) {
            if (u.getEmail().equals(newUser.getEmail())) {
                found = true;
                mEmailField.setText("");
                mPasswordField.setText("");
                mErrorMessage.setText("User already exists.");
            }
        }

        if (!found) {
            userSet.addUser(newUser);
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
        }
    }
}