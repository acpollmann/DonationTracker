package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import edu.gatech.cs2340.donationtracker.Model.ChildInfo;
import edu.gatech.cs2340.donationtracker.Model.CustomAdapter;
import edu.gatech.cs2340.donationtracker.Model.GroupInfo;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.Model.SearchAdapterLocation;
import edu.gatech.cs2340.donationtracker.R;

/**
 * Implementation that will initiate the VIEW LOCATIONS page,
 * it will collect the various locations, then display their
 * information on the list model.It will also implement the searching and
 * filtering features.
 *
 * @author Group 71B
 * @version 1.0
 */
public class ViewLocationActivity extends AppCompatActivity
        implements SearchView.OnQueryTextListener {
    private final ListModel model = ListModel.getInstance();
    private final Map<String, GroupInfo> filteredBy = new LinkedHashMap<>();
    private final ArrayList<GroupInfo> expandableListList = new ArrayList<>();
    private SearchAdapterLocation searchAdapter;
    private ExpandableListView simpleExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        View recyclerView = findViewById(R.id.locationitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        ExpandableListAdapter listAdapter = new CustomAdapter(ViewLocationActivity.this,
                expandableListList);
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
                        Toast.makeText(getBaseContext(), getString(R.string.clicked) + headerInfo.getName()
                                + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                        return false;
                    }
                });
        final int[] prevExpandPosition = {-1};
        simpleExpandableListView
                .setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
                    @Override
                    public void onGroupExpand(int groupPosition) {
                        if ((prevExpandPosition[0] >= 0) && (prevExpandPosition[0] != groupPosition)) {
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
                        Toast.makeText(getBaseContext(), getString(R.string.header) + headerInfo.getName(),
                                Toast.LENGTH_LONG).show();

                        return false;
                    }
                });
    }
    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }
    @Override
    public boolean onQueryTextChange(String newText) {
        searchAdapter.filter(newText);
        return false;
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
     *This method will load some initial data into out list
     */
    private void loadData(){
        addFilter(getString(R.string.filterlat_location), getString(R.string.searchlat_location));
        addFilter(getString(R.string.filterlat_location), getString(R.string.searchlong_location));

        addFilter(getString(R.string.locationname_location),
                getString(R.string.searchalpha_location));

        addFilter(getString(R.string.locationtype_location),
                getString(R.string.searchdrop_location));
        addFilter(getString(R.string.locationtype_location),
                getString(R.string.searchstore_location));
        addFilter(getString(R.string.locationtype_location),
                getString(R.string.searchware_location) );

        addFilter(getString(R.string.locationaddress_location),
                getString(R.string.searchalpha_location) );
        addFilter(getString(R.string.locationaddress_location),
                getString(R.string.searchclose_location) );

        addFilter(getString(R.string.locationphone_location),
                getString(R.string.searchnum_location) );
        addFilter(getString(R.string.locationphone_location),
                getString(R.string.searcharea_location) );

    }
    /**
     *This method will maintain the location filters.
     *
     * @param information the general name of the location filter
     * @param sortBy the specfic filter type
     */
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

    /**
     * This method sets up the recycler view so it can display the locations.
     *
     * @param recyclerView the recyclerview on the VIEW LOCATIONS page
     */
    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<Location> filteredLocations = model.getLocations();
        if (filteredLocations.isEmpty()) {
            Toast.makeText(ViewLocationActivity.this,
                    getString(R.string.selected_nodonations), Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(filteredLocations));
    }

    public final class SimpleItemRecyclerViewAdapter
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
            holder.mContentView.setText(mLocationList.get(position).getLocationName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), getString(R.string.info_location),
                            Toast.LENGTH_SHORT).show();
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

        @Override
        public int getItemCount() {
            return mLocationList.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private final View mView;
            private final TextView mContentView;

            /**
             * A constructor for the view
             * @param view the current view of the screen
             */
            ViewHolder(View view) {
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
    /**
     * It will change the display from the current page to ADD LOCATIONS page.
     *
     * @param view the current view of the VIEW DONATIONS page
     */
    public void onAddLocationPressed(View view) {
        Intent intent = new Intent(this, AddLocationActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.fadein, R.anim.fadeout);
    }
}
