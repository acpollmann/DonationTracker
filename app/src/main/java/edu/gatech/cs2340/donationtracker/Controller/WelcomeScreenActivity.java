package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
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
        Button registerButton = (Button) findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreenActivity.this, RegisterActivity.class));
            }
        });
    }
    private void configureSkipButton() {
        Button registerButton = (Button) findViewById(R.id.skipLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreenActivity.this, MainActivity.class));
            }
        });
    }
}
