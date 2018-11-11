package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.UserSet;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the REGISTRATION page,
 * so if the user if not already in the database, it will create
 * a new account for the otherwise an error message will be shown.
 *
 * @author Group 71B
 * @version 1.0
 */

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

        spinner = findViewById(R.id.user_spinner);
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

        setUserSet(UserSet.getInstance());
    }

    public void setUserSet(UserSet userSet) {
        this.userSet = userSet;
    }

    /**
     * It changes the page to the REGISTER page to the previous page if
     * the back button.
     */
    private void configureBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });
    }

    /**
     * This method will collect input data and set it to a variable.
     * Then it will use that information, check the data base to see if
     * the user currently exists in the system. If so, an error message
     * will be shown. If not, then the user object will be created and the
     * information will be added into the database.
     *
     * @param view the current view of the MAIN page
     */
    public void onRegisterPressed(View view) {
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        String type = spinner.getSelectedItem().toString();
        mErrorMessage.setText("");

        if ("".equals(email) || "".equals(password)) {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText(R.string.registerErrorM);
        } else if (userSet.userExists(email)) {
            mEmailField.setText("");
            mPasswordField.setText("");
            mErrorMessage.setText(R.string.registerExistingUserErrorM);
        } else {
            userSet.addUser(email, password, type);
            Intent intent = new Intent(this, WelcomeScreenActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }
}