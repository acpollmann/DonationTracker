package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the LOCATION DETAIL page and
 * display information about the specific location.
 *
 * @author Group 71B
 * @version 1.0
 */

public class LocationDetailActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mType;
    private TextView mAddress;
    private TextView latitudeLongitude;
    private TextView mPhoneNumber;
    private TextView mWebSite;

    /**
     * On the creation of the LOCATION DETAIL page, it utilizes
     * getters to collect specific information about the
     * donation item and changes the texts of the widgets in accordance.
     * @param savedInstanceState the bundle with information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        mName = findViewById(R.id.name);
        mType = findViewById(R.id.type);
        mAddress = findViewById(R.id.address);
        latitudeLongitude = findViewById(R.id.latitude_longitude);
        mPhoneNumber = findViewById(R.id.phone_number);
        mWebSite = findViewById(R.id.website);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        Log.d("bundle: ", intent.getExtras().toString());

        String name = b.getString("name");
        String type = b.getString("type");
        Log.d("Type: ", type);
        String address = b.getString("address");
        String lat_long = b.getString("latitudeLongitude");
        String phone_number = b.getString("phoneNumber");
        String website = b.getString("website");

        mName.setText(name);
        mType.setText(type);
        mAddress.setText(address);
        latitudeLongitude.setText(lat_long);
        mPhoneNumber.setText(phone_number);
        mWebSite.setText(website);
    }

    /**
     * It will change the page on the screen if the
     * back button is pressed.
     *
     * @param view the current view of the LOCATION DETAIL page
     */
    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }
}
