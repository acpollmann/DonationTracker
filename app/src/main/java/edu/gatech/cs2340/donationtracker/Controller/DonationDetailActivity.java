
package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.R;

public class DonationDetailActivity extends AppCompatActivity {
    private TextView mName;
    private TextView mLocation;
    private TextView mTimestamp;
    private TextView mValue;
    private TextView mCategory;
    private TextView mShortDescription;
    private TextView mFullDescription;
    private TextView mComments;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        mName = findViewById(R.id.name);
        mLocation = findViewById(R.id.location);
        mTimestamp = findViewById(R.id.timestamp);
        mValue = findViewById(R.id.value);
        mCategory = findViewById(R.id.category);
        mShortDescription = findViewById(R.id.shortDescription);
        mFullDescription = findViewById(R.id.fullDescription);
        mComments = findViewById(R.id.comments);


        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("name");
        String location = b.getString("location");
        String timestamp = b.getString("timestamp");
        String value = b.getString("value");
        String category = b.getString("category");
        String shortDescription = b.getString("shortDescription");
        String fullDescription = b.getString("fullDescription");
        String comments = b.getString("comments");

        mName.setText(name);
        mLocation.setText(location);
        mTimestamp.setText(timestamp);
        mValue.setText(value);
        mCategory.setText(category);
        mShortDescription.setText(shortDescription);
        mFullDescription.setText(fullDescription);
        mComments.setText(comments);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }
}
