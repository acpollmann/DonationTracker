
package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the DONATION DETAIL
 * page, change the display of the screen, and shows
 * the specific information for the donation items.
 *
 * @author Group 71B
 * @version 1.0
 */

public class DonationDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        TextView mName = findViewById(R.id.name);
        TextView mLocation = findViewById(R.id.location);
        TextView mTimestamp = findViewById(R.id.timestamp);
        TextView mValue = findViewById(R.id.value);
        TextView mCategory = findViewById(R.id.category);
        TextView mShortDescription = findViewById(R.id.shortDescription);
        TextView mFullDescription = findViewById(R.id.fullDescription);
        TextView mComments = findViewById(R.id.comments);


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

    /**
     * It will change the page on the screen if the
     * back button is pressed.
     *
     * @param view the current view of the DONATION DETAIL page
     */
    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }
}
