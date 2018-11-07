package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.content.Intent;
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

/**
 * Implementation that will initiate the ADD LOCATION page, collect
 * information, create a new location, and update the listModel and display.
 *
 * @author Group 71B
 * @version 1.0
 */

public class AddLocationActivity extends AppCompatActivity {

    private EditText mNameField;
    private EditText mStreetAddressField;
    private Spinner stateSpinner;
    private EditText mLatField;
    private EditText mLongField;
    private EditText mCityField;
    private EditText mZipField;
    private Spinner typeSpinner;
    private EditText mWebField;
    private EditText mPhoneField;

    String[] states = {
            "AK", "AL", "AR", "AZ", "CA", "CO", "CT", "DE", "FL", "GA", "HI", "IA", "ID", "IL",
            "IN", "KS", "KY", "LA", "MA", "MD", "ME", "MI", "MN", "MO", "MS", "MT", "NC", "ND",
            "NE", "NH", "NJ", "NM", "NV", "NY", "OH", "OK", "OR", "PA", "RI", "SC", "SD", "TN",
            "TX", "UT", "VA", "VT", "WA", "WI", "WV", "WY"
    };

    String[] locationTypes = {
            "Drop Off", "Store", "Warehouse"
    };


    /**
     * On the creation of the ADD LOCATION page, it will
     * change the display of the device, sets the information collected
     * from the page to fields. It also creates adapters for the spinners
     * which will be used to get information on the state and type.
     * @param savedInstanceState a bundle of information from widgets
     */
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
        typeSpinner = findViewById(R.id.typeSpinner);
        mWebField = findViewById(R.id.websiteField);
        mPhoneField = findViewById(R.id.phoneField);

         /*
          Set up the adapter to display the allowable state abbreviations in the spinner
         */
        for (String state: states) {
            Log.d("states spin", state);
        }
        ArrayAdapter<String> statesAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, states);
        statesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        stateSpinner.setAdapter(statesAdapter);

        /*
          Set up the adapter to display the allowable location types in the spinner
         */
        ArrayAdapter<String> typeAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, locationTypes);
        typeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        typeSpinner.setAdapter(typeAdapter);
    }

    /**
     * Configures the back button by creating a button instance,
     * then it creates a listener for the back button, and a transition.
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
     * Once the ADD LOCATION button is pressed, it collects the information from
     * the page and set's them to variables before creating a new location
     * object and updating the list model and page.
     * @param view the current view of the ADD LOCATION page
     */
    public void onAddLocationButtonPressed(View view) {

        String name = mNameField.getText().toString();
        double lati = Double.parseDouble(mLatField.getText().toString());
        double longi = Double.parseDouble(mLongField.getText().toString());
        String street = mStreetAddressField.getText().toString();
        String city = mCityField.getText().toString();
        String state = (String) stateSpinner.getSelectedItem();
        int zip = Integer.parseInt(mZipField.getText().toString());
        String type = (String) typeSpinner.getSelectedItem();
        String web = mWebField.getText().toString();
        String phone = mPhoneField.getText().toString();

        LocationItem newLocation = new LocationItem(name, lati, longi, street,
                city, state, zip, type, phone, web);

        ListModel.INSTANCE.addItem(newLocation);

        Intent intent = new Intent(this, ViewDonationsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }
}