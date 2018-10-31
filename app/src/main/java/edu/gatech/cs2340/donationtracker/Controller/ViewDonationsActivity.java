package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filterable;
import android.widget.Filter;
import android.widget.SearchView;
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
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.SearchAdapterDonation;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.R;

public class ViewDonationsActivity extends AppCompatActivity {
    private SearchAdapterDonation adapter;
    private List<Donation> mDonations;

    private Spinner categorySearchSpinner;
    private Spinner locationSearchSpinner;
    private SearchView searchNameView;
    private ListModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        /*Recycler View to get the details of a donation item*/
        View recyclerView = findViewById(R.id.donation_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        categorySearchSpinner = findViewById(R.id.categorySpinner);
        locationSearchSpinner = findViewById(R.id.locationSpinner);
        searchNameView = findViewById(R.id.searchView);
        model = ListModel.INSTANCE;

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        final ArrayAdapter<LocationItem> categorySearchAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Donation.searchLegalCategories);
        categorySearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySearchSpinner.setAdapter(categorySearchAdapter);
        categorySearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            View recyclerView = findViewById(R.id.donation_list);

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
        for (LocationItem location : model.getItems()) {
            selectableLocations.add(Objects.toString(location));
        }

        final ArrayAdapter<LocationItem> locationSearchAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, selectableLocations);
        locationSearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locationSearchSpinner.setAdapter(locationSearchAdapter);
        locationSearchSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            View recyclerView = findViewById(R.id.donation_list);

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
            View recyclerView = findViewById(R.id.donation_list);
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

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    private List<Donation> filterByCategory(List<Donation> donations, String filter) {
        if (filter.equals("All")) {
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

    private List<Donation> filterByLocation(List<Donation> donations, String filter) {
        if (filter.equals("All")) {
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

    private List<Donation> searchForDonation (List<Donation> donations, String search) {
        if (search == null) {
            return donations;
        }

        List<Donation> searchedDonations = new ArrayList<>();
        for (Donation donation : donations) {
            if (donation.getName().toString().toLowerCase().contains(search.toLowerCase())) {
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
        List<Donation> filteredDonations = model.getDonations();
        Context context = recyclerView.getContext();
        adapter = new SearchAdapterDonation(context, filteredDonations);
        filteredDonations = filterByCategory(filteredDonations, (String) categorySearchSpinner.getSelectedItem());
        filteredDonations = filterByLocation(filteredDonations, (String) locationSearchSpinner.getSelectedItem());
        filteredDonations = searchForDonation(filteredDonations, (String) searchNameView.getQuery().toString());


        if (filteredDonations.isEmpty()) {
            Toast.makeText(ViewDonationsActivity.this, "Selected filter doesn't have donations.", Toast.LENGTH_SHORT).show();
        }

        recyclerView.setAdapter(new SimpleDonationRecyclerViewAdapter(filteredDonations));
    }

//    /**
//     * Set up an adapter and hook it to the provided view
//     *
//     * @param searchBar_recyclerView the view that needs this adapter
//     */
//    private void setup_searchRecyclerView(RecyclerView searchBar_recyclerView) {
//        ListModel model = ListModel.INSTANCE;
//
//        searchBar_recyclerView.setAdapter(new SearchBarRecyclerViewAdapter(model.getDonations()));
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.donation_search_menu, menu);

        //MenuItem searchItem = menu.findItem(R.id.action_search);
        //SearchView searchView = (SearchView) searchItem.getActionView();

//        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                //searchBar_recyclerView.getFilter().filter(newText);
//                return false;
//            }
//        });
        return true;
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     * <p>
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    public class SimpleDonationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleDonationRecyclerViewAdapter.ViewHolder> {

        /**
         * Collection of the items to be shown in this list.
         */
        private List<Donation> mDonations;
        private List<Donation> donationListFull;
        class DListViewHolder extends RecyclerView.ViewHolder {
            public View mView;
            public TextView mContentView;

            public DListViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }
        }


        /**
         * set the items to be used by the adapter
         *
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleDonationRecyclerViewAdapter(List<Donation> items) {
            mDonations = items;
            donationListFull = new ArrayList<>(mDonations);
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
        public void onBindViewHolder(final ViewHolder holder, final int position) {
        /*
        This is where we have to bind each data element in the list (given by position parameter)
        to an element in the view (which is one of our two TextView widgets
         */
            //start by getting the element at the correct position
            holder.mDonation = mDonations.get(position);
        /*
          Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
          textview and the string rep of a course in the other.
         */
            holder.mContentView.setText(mDonations.get(position).getName());

            /*
             * set up a listener to handle if the user clicks on this list item, what should happen?
             */
            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, DonationDetailActivity.class);

                    Bundle b = new Bundle();

                    String name = "" + mDonations.get(position).getName();
                    String location = mDonations.get(position).getLocation().toString();
                    String timeStamp = mDonations.get(position).getTimeStamp();
                    String value = mDonations.get(position).getValue();
                    String category = "" + mDonations.get(position).getCategory();
                    String shortDescription = mDonations.get(position).getShortDescription();
                    String fullDescription = mDonations.get(position).getFullDescription();
                    String comments = mDonations.get(position).getComments();

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

        @Override
        public int getItemCount() {
            return mDonations.size();
        }
        public Filter getFilter() {
            return donationFilter;
        }

        private Filter donationFilter = new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                List<Donation> filteredList = new ArrayList<>();

                if (constraint == null || constraint.length() == 0) {
                    filteredList.addAll(donationListFull);
                } else {
                    String filterPattern = constraint.toString().toLowerCase().trim();

                    for (Donation donation: donationListFull) {
                        if (donation.getName().toLowerCase().startsWith(filterPattern)) {
                            filteredList.add(donation);
                        }
                    }
                }
                FilterResults results = new FilterResults();
                results.values = filteredList;

                return results;
            }

            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                mDonations.clear();
                mDonations.addAll((List) results.values);
                notifyDataSetChanged();
            }
        };


        /**
         * This inner class represents a ViewHolder which provides us a way to cache information
         * about the binding between the model element (in this case a Course) and the widgets in
         * the list view (in this case the two TextView)
         */
        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mContentView;
            public Donation mDonation;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }

    }
}

