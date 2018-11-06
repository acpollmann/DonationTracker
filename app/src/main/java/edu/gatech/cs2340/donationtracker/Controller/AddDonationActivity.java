package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.R;

public class AddDonationActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mTimestampField;
    private Spinner locationSpinner;
    private EditText mShortDescriptionField;
    private EditText mFullDescriptionField;
    private EditText mValueField;
    private Spinner categorySpinner;
    private EditText mCommentField;
    private Button uploadImageButton;
    private ImageView imageToUpload;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_donation);

        configureBackButton();

        mNameField = findViewById(R.id.nameField);
        mTimestampField = findViewById(R.id.timestampField);
        locationSpinner = findViewById(R.id.locationSpinner);
        mShortDescriptionField = findViewById(R.id.shortDescriptionField);
        mFullDescriptionField = findViewById(R.id.fullDescriptionField);
        mValueField = findViewById(R.id.valueField);
        categorySpinner = findViewById(R.id.categorySpinner);
        mCommentField = findViewById(R.id.commentField);
        uploadImageButton = findViewById(R.id.uploadImageButton);
        imageToUpload = findViewById(R.id.imageToUpload);



         /*
          Set up the adapter to display the allowable locations in the spinner
         */
        ArrayAdapter<LocationItem> locationAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ListModel.INSTANCE.getItems());
        locationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(locationAdapter);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */

        ArrayAdapter<LocationItem> categoryAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Donation.legalCategories);
        categoryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySpinner.setAdapter(categoryAdapter);


    }

    private void configureBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    public void onAddDonationButtonPressed(View view) {

        String name = mNameField.getText().toString();
        LocationItem location = (LocationItem) locationSpinner.getSelectedItem();
        String timeStamp = mTimestampField.getText().toString();
        String shortDescription = mShortDescriptionField.getText().toString();
        String fullDescription = mFullDescriptionField.getText().toString();
        String value = mValueField.getText().toString();
        String category = (String) categorySpinner.getSelectedItem();
        String comment = mCommentField.getText().toString();
        // ImageView image =

        Donation newDonation = new Donation(name, location, timeStamp, shortDescription,
                fullDescription, value, category, comment);

        ListModel.INSTANCE.addDonationItem(newDonation);

        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

}
