package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.Locale;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the WELCOME page which
 * displays 2 buttons. One for the login page the other for the
 * registration page.
 * email/password.
 *
 * @author Group 71B
 * @version 1.0
 */
public class WelcomeScreenActivity extends AppCompatActivity {
    private LinearLayout welcomeScreen;
    private AnimationDrawable anim;
    private Locale myLocale;
    private String currentLanguage = "en";
    String currentLang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_screen2);
        welcomeScreen = findViewById(R.id.welcomeScreen);
        welcomeScreen.setBackgroundResource(R.drawable.background_anim);
        anim = (AnimationDrawable) welcomeScreen.getBackground();
        int fadeDuration = 4500;
        anim.setEnterFadeDuration(fadeDuration);
        anim.setExitFadeDuration(fadeDuration);
        Button registerButton = findViewById(R.id.register_button);
        Animation fromTop = AnimationUtils.loadAnimation(this, R.anim.frombottom);
        registerButton.startAnimation(fromTop);
        configureRegistrationButton();
        configureLoginButton();
        configureSkipButton();
        addListenerOnSpinnerItemSelection();
    }
    /**
     * It sets up the login button, so if pressed it will redirect to the LOGIN page.
     */
    private void configureLoginButton() {
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

    /**
     * It sets up the registration button, so if pressed it will redirect to the REGISTRATION page.
     */
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

    /**
     * It sets up the skip button, so if pressed it will redirect to the MAIN page.
     */
    private void configureSkipButton() {
        Button registerButton = findViewById(R.id.skipLogin);
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(WelcomeScreenActivity.this, MainActivity.class));
            }
        });
    }

    /**
     * It sets up the listener, so if a user changes the language spinner the app will
     * know to change the language.
     */
<<<<<<< HEAD
    public void addListenerOnSpinnerItemSelection() {
=======
    private void addListenerOnSpinnerItemSelection() {
>>>>>>> 818cdbc4bce7aff1e920ff30f1b83b177548a3f4
        Spinner langspinner = findViewById(R.id.languagespinner);
        langspinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());
    }
    class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            if (pos == 0) {
                setAppLocale("en");
            }
            if (pos == 1) {
                Toast.makeText(parent.getContext(), "You have selected Español",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("es");
            }
            if (pos == 2) {
                Toast.makeText(parent.getContext(), "You have selected Français",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("fr");
            }
            if (pos == 3) {
                Toast.makeText(parent.getContext(), "You have selected 日本人",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("ja");
            }
            if (pos == 4) {
                Toast.makeText(parent.getContext(), "You have selected 한국어",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("ko");
            }
            if (pos == 5) {
                Toast.makeText(parent.getContext(), "You have selected Pусский",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("ru");
            }
            if (pos == 6) {
                Toast.makeText(parent.getContext(), "You have selected 普通话",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("zh");
            }
            if (pos == 7) {
                Toast.makeText(parent.getContext(), "You have selected English",
                        Toast.LENGTH_SHORT).show();
                setAppLocale("en-rCA");
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
        }
    }
    /**
     * A method for the configuration fo the app when language is changed.
     *
     * @param lang the specific language
     */
    private void setAppLocale(String lang) {
        if (!lang.equals(currentLanguage)) {
            myLocale = new Locale(lang);
            Resources res = getResources();
            DisplayMetrics dm = res.getDisplayMetrics();
            Configuration conf = res.getConfiguration();
            conf.locale = myLocale;
            res.updateConfiguration(conf, dm);
            Intent refresh = new Intent(this, WelcomeScreenActivity.class);
            refresh.putExtra(currentLang, lang);
            startActivity(refresh);
        }
    }
}
