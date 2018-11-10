
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
    private TextView mLocation;
    private TextView mTimestamp;
    private TextView mValue;
    private TextView mCategory;
    private TextView mShortDescription;
    private TextView mFullDescription;
    private TextView mComments;

    /**
     * On the creation of the DONATION DETAIL page, it utilizes
     * getters to collect specific information about the
     * donation item and changes the texts of the widgets in accordance.
     * @param savedInstanceState the bundle with information
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation_detail);

        TextView mName = findViewById(R.id.name);
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
