package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import edu.gatech.cs2340.donationtracker.Model.ChildInfo;
import edu.gatech.cs2340.donationtracker.Model.CustomAdapter;
import edu.gatech.cs2340.donationtracker.Model.GroupInfo;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.Model.SearchAdapterLocation;
import edu.gatech.cs2340.donationtracker.R;

//public class ViewLocationActivity extends AppCompatActivity {
//public class ViewLocationActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
public class ViewLocationActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {

    ListModel model = ListModel.getInstance();
    private final LinkedHashMap<String, GroupInfo> filteredBy = new LinkedHashMap<>();
    private final ArrayList<GroupInfo> expandableListList = new ArrayList<>();
    private CustomAdapter listAdapter;
    private SearchAdapterLocation searchAdapter;
    private ExpandableListView simpleExpandableListView;
    private SearchView searchNameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        searchNameView = findViewById(R.id.searchView);
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(ViewLocationActivity.this, expandableListList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);
        // setOnChildClickListener listener for child row click
        simpleExpandableListView
                .setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition,
                                        int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = expandableListList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        final int[] prevExpandPosition = {-1};
        simpleExpandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                    simpleExpandableListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView
                .setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v,
                                        int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = expandableListList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });
    }
    @Override
    public boolean onQueryTextSubmit(String query) {
        searchNameView.setOnQueryTextListener(new android.widget.SearchView.OnQueryTextListener() {
            View recyclerView = findViewById(R.id.locationitem_list);
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
        return true;
    }

    @Override
    public boolean onQueryTextChange(String s) {
        return false;
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    //load some initial data into out list
    private void loadData(){
        addFilter(getString(R.string.filterlat_location), getString(R.string.searchlat_location) );
        addFilter(getString(R.string.filterlat_location), getString(R.string.searchlong_location) );

        addFilter(getString(R.string.locationname_location), getString(R.string.searchalpha_location) );

        addFilter(getString(R.string.locationtype_location), getString(R.string.searchdrop_location) );
        addFilter(getString(R.string.locationtype_location), getString(R.string.searchstore_location) );
        addFilter(getString(R.string.locationtype_location), getString(R.string.searchware_location) );

        addFilter(getString(R.string.locationaddress_location), getString(R.string.searchalpha_location) );
        addFilter(getString(R.string.locationaddress_location), getString(R.string.searchclose_location) );

        addFilter("Location Address","Sort Alphabetically");
        addFilter("Location Address","Sort By Closeness");
        addFilter("Location Name","Sort Alphabetically");

        addFilter("Location Type","Sort By Drop-Off");
        addFilter("Location Type","Sort By Store");
        addFilter("Location Type","Sort By Warehouse");

        addFilter("Location Address","Sort Alphabetically");
        addFilter("Location Address","Sort By Closeness");

        addFilter("Location Phone Number","Sort Numerically");
        addFilter("Location Phone Number","Sort By Area Code");

        addFilter(getString(R.string.locationphone_location), getString(R.string.searchnum_location) );
        addFilter(getString(R.string.locationphone_location), getString(R.string.searcharea_location) );

        addFilter("Location Phone Number","Sort Numerically");
        addFilter("Location Phone Number","Sort By Area Code");
    }

    /**
     * Allows text in search bar to create a list from donations
     * that contains the text typed into the bar
     * @param locations list of donations
     * @param search string fragment used to search donations
     * @return the list of searched donations
     */
    private List<Location> searchForLocation (List<Location> locations, String search) {
        if (search == null) {
            return locations;
        }

        List<Location> searchedLocations = new ArrayList<>();
        for (Location location : locations) {
            if (location.getLocationName().toLowerCase().contains(search.toLowerCase())) {
                searchedLocations.add(location);
            }
        }
        return searchedLocations;
    }

    //here we maintain our products in various departments
    private void addFilter(String information, String sortBy) {
        //check the hash map if the group already exists
        GroupInfo headerInfo = filteredBy.get(information);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(information);
            filteredBy.put(information, headerInfo);
            expandableListList.add(headerInfo);
        }

        //get the children for the group
        ArrayList<ChildInfo> productList = headerInfo.getProductList();
        //size of the children list
        int listSize = productList.size();
        //add to the counter
        listSize++;

        //create a new child and add that to the group
        ChildInfo detailInfo = new ChildInfo();
        detailInfo.setSequence(String.valueOf(listSize));
        detailInfo.setName(sortBy);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<Location> filteredLocations = model.getLocations();

        filteredLocations = searchForLocation(filteredLocations, searchNameView.getQuery().toString());

        if (filteredLocations.isEmpty()) {
            Toast.makeText(ViewLocationActivity.this, "Selected filter doesn't have donations.", Toast.LENGTH_SHORT).show();
            if (filteredLocations.isEmpty()) {
                Toast.makeText(ViewLocationActivity.this,
                        "Selected filter doesn't have donations.", Toast.LENGTH_SHORT).show();
            }
            recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(filteredLocations));
        }
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<Location> mLocationList;

        private SimpleItemRecyclerViewAdapter(List<Location> items) {
            mLocationList = items;
        }

        @Override @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.locationitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {

            /*
        This is where we have to bind each data element in the list (given by position parameter)
        to an element in the view (which is one of our two TextView widgets
         */
            //start by getting the element at the correct position
            holder.mLocation = mLocationList.get(position);
        /*
          Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
          textview and the string rep of a course in the other.
         */
            holder.mContentView.setText(mLocationList.get(position).getLocationName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Toast.makeText(getApplication(), "Location Info", Toast.LENGTH_SHORT).show();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);

                    Bundle b = new Bundle();

                    String name = "" + mLocationList.get(position).getLocationName();
                    String type = "" + mLocationList.get(position).getType();
                    String address = mLocationList.get(position).getAddress();
                    String latitudeLongitude = mLocationList.get(position).getLatitude() + "/"
                            + "" + mLocationList.get(position).getLongitude();
                    String phoneNumber = mLocationList.get(position).getPhone();
                    String website = mLocationList.get(position).getWebsite();
                    b.putString("name", name);
                    b.putString("type", type);
                    b.putString("address", address);
                    b.putString("latitudeLongitude", latitudeLongitude);
                    b.putString("phoneNumber", phoneNumber);
                    b.putString("website", website);

                    intent.putExtras(b);
                    context.startActivity(intent);
                }
            });
        }

        /**
         * Returns the number of elements in mLocationList
         * @return mLocationList.size() the num of elements
         */
        @Override
        public int getItemCount() {
            return mLocationList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final View mView;
            private final TextView mContentView;
            public Location mLocation;

            public ViewHolder(View view) {
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
    public void onAddLocationPressed(View view) {
        Intent intent = new Intent(this, AddLocationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
