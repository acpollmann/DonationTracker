package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.R;

public class LocationDetailActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mType;
    private TextView mAddress;
    private TextView latitudeLongitude;
    private TextView mPhoneNumber;
    private TextView mWebSite;

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
        String name = b.getString("name");
        String type = b.getString("type");
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

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }
}
