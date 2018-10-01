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
import java.util.Map;
import java.util.HashMap;

import edu.gatech.cs2340.donationtracker.Model.UserMap;
import edu.gatech.cs2340.donationtracker.R;

public class RegisterActivity extends AppCompatActivity {

    private EditText mUsernameField;
    private EditText mEmailField;
    private EditText mPasswordField;
    private TextView mErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Spinner spinner = (Spinner) findViewById(R.id.user_spinner);
    // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.user_array, android.R.layout.simple_spinner_item);
    // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    // Apply the adapter to the spinner
        spinner.setAdapter(adapter);

        configureBackButton();
        configureSaveRegistrationButton();

        mUsernameField = findViewById(R.id.name_field);
        mEmailField = findViewById(R.id.email_field);
        mPasswordField = findViewById(R.id.password_field);
        mErrorMessage = findViewById(R.id.error_message);
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

    private void configureSaveRegistrationButton() {
        Button backButton = (Button) findViewById(R.id.saveRegistrationButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(RegisterActivity.this, MainActivity.class));
            }
        });
    }

    private void onRegisterPressed(View view) {
        String username = mUsernameField.getText().toString();
        String email = mEmailField.getText().toString();
        String password = mPasswordField.getText().toString();
        mErrorMessage.setText("");

        Map<String, String> findByEmail = UserMap.usersByEmail(username, email, password);
        Map<String, String> findByName = UserMap.usersByName(username, email, password);

        findByEmail.put(email, password);
        findByName.put(username, password);
    }
}
