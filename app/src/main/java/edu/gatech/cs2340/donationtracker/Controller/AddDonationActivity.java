package edu.gatech.cs2340.donationtracker.Controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import java.util.List;

import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.ListModel;

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
    private Button addDonationButton;

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
        addDonationButton = findViewById(R.id.addDonationButton);



         /*
          Set up the adapter to display the allowable locations in the spinner
         */
         Log.d("Hello:", ListModel.INSTANCE.getItems().toString());
        ArrayAdapter<LocationItem> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ListModel.INSTANCE.getItems());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSpinner.setAdapter(adapter);


    }

    private void configureBackButton() {
        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }


}
