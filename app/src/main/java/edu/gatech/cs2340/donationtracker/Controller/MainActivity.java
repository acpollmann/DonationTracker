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

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
    }

    public void onLogoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
    }

    public void onViewLocationButtonPressed(View view) {
        Button viewLocationButton = (Button) findViewById(R.id.view_location_bubble);
        viewLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //startActivity(new Intent(MainActivity.this, ViewLocationActivity.class));
                startActivity(new Intent(MainActivity.this, ViewLocationActivity.class));
            }
        });
    }
}
