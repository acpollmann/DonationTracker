package edu.gatech.cs2340.donationtracker.Controller;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.facebook.login.LoginManager;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the MAIN page and
 * display different buttons leading to different pages
 * so the user can access the app's different features.
 *
 * @author Group 71B
 * @version 1.0
 */
public class MainActivity extends AppCompatActivity {

    private GoogleSignInClient mGoogleSignInClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Configure sign-in to request the user's ID, email address, and basic
        // profile. ID and basic profile are included in DEFAULT_SIGN_IN.
        GoogleSignInOptions gso =
                new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                        .requestEmail()
                        .build();

        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    /**
     * It changes the page to the original WELCOME page for the app if
     * the logout button is pressed.
     *
     * @param view the current view of the MAIN page
     */
    public void onLogoutPressed(View view) {
        LoginManager.getInstance().logOut();

        mGoogleSignInClient.signOut().addOnCompleteListener(this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(MainActivity.this, WelcomeScreenActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.fadein, R.anim.fadeout);
            }
        });

        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }

    /**
     * It changes the page to the MAP VIEW page for the app if
     * the MAP VIEW button is pressed.
     *
     * @param view the current view of the MAIN page
     */
    public void onMapViewButtonPressed(View view) {
        animateButtons();
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }
    /**
     * It changes the page to the VIEW LOCATION page for the app if
     * the VIEW LOCATION button is pressed.
     *
     * @param view the current view of the MAIN page
     */
    public void onViewLocationButtonPressed(View view) {
        animateButtons();
        Intent intent = new Intent(this, ViewLocationActivity.class);
        finish();
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }

    /**
     * It changes the page to the ADD DONATION page for the app if
     * the ADD DONATION button is pressed.
     *
     * @param view the current view of the MAIN page
     */
    public void onAddDonationButtonPressed(View view) {
        animateButtons();
        Intent intent = new Intent(this, AddDonationActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }
    private final Runnable task = new Runnable() {
        @Override
        public void run() {
            recreate();
        }
    };

    /**
     * It changes the page to the SEARCH DONATIONS page for the app if
     * the SEARCH DONATIONS button is pressed.
     *
     * @param view the current view of the MAIN page
     */
    public void onSearchDonationButtonPressed(View view) {
        animateButtons();
        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }

    /**
     * Method for the animation of the buttons when they are pressed.
     * Specifically, details their path during animation.
     *
     */
    private void animateButtons() {
        ObjectAnimator viewanimator;
        ObjectAnimator addanimator;
        ObjectAnimator statanimator;
        ObjectAnimator mananimator;
        ObjectAnimator mapanimator;
        ObjectAnimator searchanimator;
        Button viewLocationTarget = this.findViewById(R.id.view_location_bubble);
        Button addDonationTarget = this.findViewById(R.id.add_donation_bubble);
        Button statsTarget = this.findViewById(R.id.statistics_bubble);
        Button manageTarget = this.findViewById(R.id.manage_accounts_bubble);
        Button mapTarget = this.findViewById(R.id.map_view_bubble);
        Button searchTarget = this.findViewById(R.id.search_for_donation_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 800f, 1100f, -24f, 14000f, false);
            viewanimator = ObjectAnimator.ofFloat(viewLocationTarget, View.X, View.Y, path);
            viewanimator.setDuration(500);
            viewanimator.start();
            Path path2 = new Path();
            path2.arcTo(0f, 0f, 700f, 1700f, -145f, 14000f, false);
            addanimator = ObjectAnimator.ofFloat(addDonationTarget, View.X, View.Y, path2);
            addanimator.setDuration(500);
            addanimator.start();
            Path path3 = new Path();
            path3.arcTo(0f, 0f, 400f, 2300f, -5f, 14000f, false);
            statanimator = ObjectAnimator.ofFloat(statsTarget, View.X, View.Y, path3);
            statanimator.setDuration(500);
            statanimator.start();
            Path path4 = new Path();
            path4.arcTo(0f, 0f, 500f, 1170f, -50f, 12000f, false);
            mananimator =
                    ObjectAnimator.ofFloat(manageTarget, View.X, View.Y, path4);
            mananimator.setDuration(500);
            mananimator.start();
            Path path5 = new Path();
            path5.arcTo(0f, 0f, 110f, 1450f, 20f, 14000f, false);
            mapanimator = ObjectAnimator.ofFloat(mapTarget, View.X, View.Y, path5);
            mapanimator.setDuration(500);
            mapanimator.start();
            Path path6 = new Path();
            path6.arcTo(0f, 0f, 770f, 1150f, 40f, 14000f, false);
            searchanimator =
                    ObjectAnimator.ofFloat(searchTarget, View.X, View.Y, path6);
            searchanimator.setDuration(500);
            searchanimator.start();
        }  else {
            Animation ifoldanimation = AnimationUtils.loadAnimation(this, R.anim.rotateleft);
            viewLocationTarget.startAnimation(ifoldanimation);
            searchTarget.startAnimation(ifoldanimation);
            addDonationTarget.startAnimation(ifoldanimation);
            mapTarget.startAnimation(ifoldanimation);
        }
    }
}

