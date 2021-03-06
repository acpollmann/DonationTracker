package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.DonationModel;
import edu.gatech.cs2340.donationtracker.Model.LocationModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the ADD DONATION page and
 * if given valid data, will create a donation item then change
 * the list model on the SEARCH DONATION page.
 *
 * @author Group 71B
 * @version 1.0
 */

public class AddDonationActivity extends AppCompatActivity {

    private TextView mErrorMessage;

    private EditText mNameField;
    private EditText mTimestampField;
    private Spinner locationSpinner;
    private EditText mShortDescriptionField;
    private EditText mFullDescriptionField;
    private EditText mValueField;
    private Spinner categorySpinner;
    private EditText mCommentField;

    private DonationModel donationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        configureBackButton();

        mErrorMessage = findViewById(R.id.error_message);

        mNameField = findViewById(R.id.nameField);
        mTimestampField = findViewById(R.id.timestampField);
        locationSpinner = findViewById(R.id.locationSpinner);
        mShortDescriptionField = findViewById(R.id.shortDescriptionField);
        mFullDescriptionField = findViewById(R.id.fullDescriptionField);
        mValueField = findViewById(R.id.valueField);
        categorySpinner = findViewById(R.id.categorySpinner);
        mCommentField = findViewById(R.id.commentField);

        donationModel = DonationModel.getInstance();
        LocationModel locationModel = LocationModel.getInstance();

         /*
          Set up the adapter to display the allowable locations in the spinner
         */
        ArrayAdapter<Location> locationAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, locationModel.getLocations());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        ArrayAdapter<String> categoryAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Donation.legalCategories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);
    }

    /**
     *Allows users to return to the main page from the ADD DONATION page.
     *Utilizes a listener to determine is the user wants to return.
     */
    private void configureBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                overridePendingTransition(R.anim.slideright, R.anim.slideleft);
            }
        });
    }

    /**
     * Will utilize information from the screen, create a new donation,
     * and then update the list model as well as the display.
     * @param view the current view of the ADD DONATION page
     */
    public void onAddDonationButtonPressed(View view) {

        String name = mNameField.getText().toString();
        Location location = (Location) locationSpinner.getSelectedItem();
        String timeStamp = mTimestampField.getText().toString();
        String shortDescription = mShortDescriptionField.getText().toString();
        String fullDescription = mFullDescriptionField.getText().toString();
        String value = mValueField.getText().toString();
        String category = (String) categorySpinner.getSelectedItem();
        String comment = mCommentField.getText().toString();

        if ("".equals(name) || "".equals(timeStamp) || "".equals(shortDescription)
                || "".equals(fullDescription) || "".equals(value)) {
            mErrorMessage.setText(R.string.addDonationErrorM);
        } else {
            donationModel.addDonation(name, location, timeStamp, shortDescription,
                    fullDescription, value, category, comment);
            Intent intent = new Intent(this, ViewDonationsActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.fadein, R.anim.fadeout);
        }
    }

}
