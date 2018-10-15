package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import edu.gatech.cs2340.donationtracker.R;

public class DonationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
    }
}
