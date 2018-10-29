package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import edu.gatech.cs2340.donationtracker.R;

public class WelcomeScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen2);
        configureRegistrationButton();
        configureSkipButton();
    }

    public void login(View view) {
        Intent changeLogin = new Intent(this, LoginActivity.class);
        startActivity(changeLogin);
    }
    private void configureRegistrationButton() {
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreenActivity.this, RegisterActivity.class));
            }
        });
    }
    private void configureSkipButton() {
        Button registerButton = findViewById(R.id.skipLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreenActivity.this, MainActivity.class));
            }
        });
    }
}
