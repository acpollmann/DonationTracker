package edu.gatech.cs2340.donationtracker.Controller;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import edu.gatech.cs2340.donationtracker.R;

public class ViewLocationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
    }
    Intent intent = getIntent();
//    if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
//        String query = intent.getStringExtra(SearchManager.QUERY);
//        doMySearch(query);
//    }
}
