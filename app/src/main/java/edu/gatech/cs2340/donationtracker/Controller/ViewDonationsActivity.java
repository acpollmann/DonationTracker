package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.SearchView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.DonationModel;
import edu.gatech.cs2340.donationtracker.Model.LocationModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the VIEW DONATIONS page,
 * it will collect the various donation items, then display them
 * on the list model.It will also implement the searching and
 * filtering features to organize all the donations.
 *
 * @author Group 71B
 * @version 1.0
 */
public class ViewDonationsActivity extends AppCompatActivity {

    private Spinner categorySearchSpinner;
    private Spinner locationSearchSpinner;
    private SearchView searchNameView;

    private DonationModel donationModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        donationModel = DonationModel.getInstance();
        LocationModel locationModel = LocationModel.getInstance();

        categorySearchSpinner = findViewById(R.id.categorySpinner);
        locationSearchSpinner = findViewById(R.id.locationSpinner);
        searchNameView = findViewById(R.id.searchView);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        final ArrayAdapter<String> categorySearchAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, Donation.searchLegalCategories);
        categorySearchAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        categorySearchSpinner.setAdapter(categorySearchAdapter);
        categorySearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final View recyclerView = findViewById(R.id.donation_list);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        /*
          Set up the adapter to display the locations in the spinner
         */
        List<String> selectableLocations = new ArrayList<>();
        selectableLocations.add("All");

        for (Location location : locationModel.getLocations()) {
            selectableLocations.add(Objects.toString(location));
        }
        final ArrayAdapter<String> locationSearchAdapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item, selectableLocations);
        locationSearchAdapter.setDropDownViewResource(
                android.R.layout.simple_spinner_dropdown_item
        );
        locationSearchSpinner.setAdapter(locationSearchAdapter);
        locationSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            final View recyclerView = findViewById(R.id.donation_list);

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                assert recyclerView != null;
                setupRecyclerView((RecyclerView) recyclerView);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        searchNameView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            final View recyclerView = findViewById(R.id.donation_list);
            @Override
            public boolean onQueryTextSubmit(String query) {
                setupRecyclerView((RecyclerView) recyclerView);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                setupRecyclerView((RecyclerView) recyclerView);
                return false;
            }
        });

    }
    /**
     * It will change the display from the current page to the previous page.
     *
     * @param view the current view of the VIEW DONATIONS page
     */
    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    /**
     * This method will iterate through the donations list and then based on the filter
     * it will return a list with donations that fit the criteria. (CATEGORY)
     *
     * @param donations the list of all the donations in the app
     * @param filter a string that each donation item should have in order to be
     * returned in the final filtered list (category type)
     */
    private List<Donation> filterByCategory(List<Donation> donations, String filter) {
        if ("All".equals(filter)) {
            return donations;
        }

        List<Donation> filteredByCategory = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.getCategory().equals(filter)) {
                filteredByCategory.add(donation);
            }
        }
        return filteredByCategory;
    }

    /**
     * This method will iterate through the donations list and then based on the filter
     * it will return a list with donations that fit the criteria. (LOCATION)
     *
     * @param donations the list of all the donations in the app
     * @param filter a string that each donation item should have in order to be
     * returned in the final filtered list (location)
     */
    private List<Donation> filterByLocation(List<Donation> donations, String filter) {
        if ("All".equals(filter)) {
            return donations;
        }

        List<Donation> filteredByLocation = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.getLocation().toString().equals(filter)) {
                filteredByLocation.add(donation);
            }
        }

        return filteredByLocation;
    }

    /**
     * This method will take user text input and search for donation items with names
     * matching the string.
     *
     * @param donations the list of all the donations in the app
     * @param search a string from the user that will be used to filter (DONATION NAME)
     */
    private List<Donation> searchForDonation (List<Donation> donations, String search) {
        if (search == null) {
            return donations;
        }

        List<Donation> searchedDonations = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.getName().toLowerCase().contains(search.toLowerCase())) {
                searchedDonations.add(donation);
            }
        }

        return searchedDonations;
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView the view that needs this adapter
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        List<Donation> filteredDonations = donationModel.getDonations();

        filteredDonations = filterByCategory(
                            filteredDonations,
                            (String) categorySearchSpinner.
                                    getSelectedItem());
        filteredDonations = filterByLocation(
                            filteredDonations,
                            (String) locationSearchSpinner.
                                    getSelectedItem());
        filteredDonations = searchForDonation(
                            filteredDonations,
                            searchNameView.getQuery().toString());

        if (filteredDonations.isEmpty()) {
            Toast.makeText(
                    ViewDonationsActivity.this,
                    getString(R.string.selected_nodonations),
                    Toast.LENGTH_SHORT).show();
        }

        recyclerView.setAdapter(new SimpleDonationRecyclerViewAdapter(filteredDonations));
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     * <p>
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    private final class SimpleDonationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleDonationRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private final List<Donation> mDonationList;


        /**
         * set the items to be used by the adapter
         *
         * @param items the list of items to be displayed in the recycler view
         */
        private SimpleDonationRecyclerViewAdapter(List<Donation> items) {
            mDonationList = items;
        }

        @Override @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        /*
          This sets up the view for each individual item in the recycler display
          To edit the actual layout, we would look at: res/layout/course_list_content.xml
          If you look at the example file, you will see it currently just 2 TextView elements
         */
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.donation_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        /*
        This is where we have to bind each data element in the list (given by position parameter)
        to an element in the view (which is one of our two TextView widgets
         */
            //start by getting the element at the correct position
            holder.mDonation = mDonationList.get(position);
        /*
          Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
          textview and the string rep of a course in the other.
         */
            holder.mContentView.setText(mDonationList.get(position).getName());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DonationDetailActivity.class);

                    Bundle b = new Bundle();

                    String name = "" + mDonationList.get(position).getName();
                    String location = mDonationList.get(position).getLocation().toString();
                    String timeStamp = mDonationList.get(position).getTimeStamp();
                    String value = mDonationList.get(position).getValue();
                    String category = "" + mDonationList.get(position).getCategory();
                    String shortDescription = mDonationList.get(position).getShortDescription();
                    String fullDescription = mDonationList.get(position).getFullDescription();
                    String comments = mDonationList.get(position).getComments();

                    b.putString("name", name);
                    b.putString("location", location);
                    b.putString("timestamp", timeStamp);
                    b.putString("value", value);
                    b.putString("category", category);
                    b.putString("shortDescription", shortDescription);
                    b.putString("fullDescription", fullDescription);
                    b.putString("comments", comments);

                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }

        /**
         * Returns the number of elements in mDonationList
         * @return mDonationList.size() the bu
         */
        @Override
        public int getItemCount() {
            return mDonationList.size();
        }

        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */
        public final class ViewHolder extends RecyclerView.ViewHolder {
            private final View mView;
            private final TextView mContentView;
            Donation mDonation;

            private ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }

    }
}

