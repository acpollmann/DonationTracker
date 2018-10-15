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
    private TextView Address;
    private TextView latitudeLongitude;
    private TextView phoneNumber;
    private TextView webSite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        mName = findViewById(R.id.name);
        Address = findViewById(R.id.address);
        latitudeLongitude = findViewById(R.id.latitute_longitude);
        phoneNumber = findViewById(R.id.phone_number);
        webSite = findViewById(R.id.website);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("name");
        String address = b.getString("address");
        String lat_long = b.getString("latitude_longitude");
        String phone_number = b.getString("phone number");
        String website = b.getString("website");

        mName.setText(name);
        Address.setText(address);
        latitudeLongitude.setText(lat_long);
        phoneNumber.setText(phone_number);
        webSite.setText(website);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
    }
}

