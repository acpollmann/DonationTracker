package edu.gatech.cs2340.donationtracker.Controller;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Path;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import edu.gatech.cs2340.donationtracker.R;


public class MainActivity extends AppCompatActivity {

    public static String TAG = "Donation_TrackerAPP";
    ObjectAnimator animator;
    ObjectAnimator animator2;
    ObjectAnimator animator3;
    ObjectAnimator animator4;
    ObjectAnimator animator5;
    ObjectAnimator animator6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    public void onMapViewButtonPressed(View view) {
        animateButtons(view);
        Intent intent = new Intent(this, MapsActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }

    public void onViewLocationButtonPressed(View view) {
        animateButtons(view);
        Intent intent = new Intent(this, ViewLocationActivity.class);
        finish();
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }

    public void onAddDonationButtonPressed(View view) {
        animateButtons(view);
        Intent intent = new Intent(this, AddDonationActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }
    private final Runnable task = new Runnable() {
        public void run() {
            recreate();
        }
    };

    public void onSearchDonationButtonPressed(View view) {
        animateButtons(view);
        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        Handler handler = new Handler();
        int millisDelay = 1000;
        handler.postDelayed(task, millisDelay);
    }

    public void animateButtons(View view) {
        Button viewLocationTarget = (Button) this.findViewById(R.id.view_location_bubble);
        Button addDonationTarget = (Button) this.findViewById(R.id.add_donation_bubble);
        Button statsTarget = (Button) this.findViewById(R.id.statistics_bubble);
        Button manageTarget = (Button) this.findViewById(R.id.manage_accounts_bubble);
        Button mapTarget = (Button) this.findViewById(R.id.map_view_bubble);
        Button searchTarget = (Button) this.findViewById(R.id.search_for_donation_button);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Path path = new Path();
            path.arcTo(0f, 0f, 800f, 1100f, -24f, 14000f, false);
            animator = ObjectAnimator.ofFloat(viewLocationTarget, view.X, view.Y, path);
            animator.setDuration(500);
            animator.start();
            Path path2 = new Path();
            path2.arcTo(0f, 0f, 700f, 1700f, -145f, 14000f, false);
            animator2 = ObjectAnimator.ofFloat(addDonationTarget, view.X, view.Y, path2);
            animator2.setDuration(500);
            animator2.start();
            Path path3 = new Path();
            path3.arcTo(0f, 0f, 400f, 2300f, -5f, 14000f, false);
            animator3 = ObjectAnimator.ofFloat(statsTarget, view.X, view.Y, path3);
            animator3.setDuration(500);
            animator3.start();
            Path path4 = new Path();
            path4.arcTo(0f, 0f, 500f, 1170f, -50f, 12000f, false);
            animator4 = ObjectAnimator.ofFloat(manageTarget, manageTarget.X, manageTarget.Y, path4);
            animator4.setDuration(500);
            animator4.start();
            Path path5 = new Path();
            path5.arcTo(0f, 0f, 110f, 1450f, 20f, 14000f, false);
            animator5 = ObjectAnimator.ofFloat(mapTarget, mapTarget.X, mapTarget.Y, path5);
            animator5.setDuration(500);
            animator5.start();
            Path path6 = new Path();
            path6.arcTo(0f, 0f, 770f, 1150f, 40f, 14000f, false);
            animator6 = ObjectAnimator.ofFloat(searchTarget, searchTarget.X, searchTarget.Y, path6);
            animator6.setDuration(500);
            animator6.start();
        } else {
            Animation animationnext = AnimationUtils.loadAnimation(this, R.anim.rotateleft);
            viewLocationTarget.startAnimation(animationnext);
        }
    }
}
