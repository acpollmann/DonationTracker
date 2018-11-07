package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import edu.gatech.cs2340.donationtracker.R;


public class WelcomeScreenActivity extends AppCompatActivity {
    LinearLayout welcomeScreen;
    AnimationDrawable anim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen2);
        welcomeScreen = (LinearLayout) findViewById(R.id.welcomeScreen);
        welcomeScreen.setBackgroundResource(R.drawable.background_anim);
        anim = (AnimationDrawable) welcomeScreen.getBackground();
        anim.setEnterFadeDuration(4500);
        anim.setExitFadeDuration(4500);
        Button registerButton = findViewById(R.id.register_button);
        Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        registerButton.startAnimation(fromTop);
        configureRegistrationButton();
        configureLoginButton();
        configureSkipButton();
    }

    public void configureLoginButton() {
        Button loginButton = findViewById(R.id.login_button);
        Animation fromBottom = AnimationUtils.loadAnimation(this, R.anim.fromtop);
        loginButton.startAnimation(fromBottom);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.start();
                startActivity(new Intent(WelcomeScreenActivity.this, LoginActivity.class));
            }
        });
    }
    private void configureRegistrationButton() {
        Button registerButton = findViewById(R.id.register_button);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                anim.start();
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
