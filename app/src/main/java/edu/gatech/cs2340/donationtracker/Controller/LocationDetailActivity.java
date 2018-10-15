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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_detail);

        mName = findViewById(R.id.name);

        Intent intent = getIntent();
        Bundle b = intent.getExtras();
        String name = b.getString("name");

        mName.setText(name);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, ViewLocationActivity.class);
        startActivity(intent);
    }
}

