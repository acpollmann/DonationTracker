package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.util.Log;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.nio.charset.StandardCharsets;

        import edu.gatech.cs2340.donationtracker.R;

        import edu.gatech.cs2340.donationtracker.Model.ListModel;
        import edu.gatech.cs2340.donationtracker.Model.LocationItem;

public class MainActivity extends AppCompatActivity {

    public static String TAG = "Donation_TrackerAPP";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onLogoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    public void onViewLocationButtonPressed(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
    }

    public void onAddDonationButtonPressed(View view) {
        Intent intent = new Intent(this, AddDonationActivity.class);
        startActivity(intent);
    }
}
