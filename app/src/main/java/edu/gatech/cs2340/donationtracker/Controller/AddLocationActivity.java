package edu.gatech.cs2340.donationtracker.Controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.R;

public class AddLocationActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mStreetAddressField;
    private Spinner stateSpinner;
    private EditText mLatField;
    private EditText mLongField;
    private EditText mCityField;
    private EditText mZipField;
    private EditText mWebField;
    private EditText mPhoneField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_location);

        configureBackButton();

        mNameField = findViewById(R.id.nameField);
        mLatField = findViewById(R.id.LatField);
        mLongField = findViewById(R.id.LongField);
        mStreetAddressField = findViewById(R.id.addressField);
        mCityField = findViewById(R.id.CityField);
        stateSpinner = findViewById(R.id.StateSpinner);
        mZipField = findViewById(R.id.ZipField);
        mWebField = findViewById(R.id.websiteField);
        mPhoneField = findViewById(R.id.phoneField);



         /*
          Set up the adapter to display the allowable locations in the spinner
         */
        Log.d("Hello:", ListModel.INSTANCE.getItems().toString());
        ArrayAdapter<LocationItem> adapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, ListModel.INSTANCE.getItems());
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(adapter);
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
