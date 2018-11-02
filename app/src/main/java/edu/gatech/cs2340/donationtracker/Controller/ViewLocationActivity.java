//<Search
//        android:id="@+id/searchView"
//        android:layout_width="301dp"
//        android:layout_height="42dp"
//        android:layout_marginEnd="20dp"
//        android:layout_marginStart="40dp"
//        app:layout_constraintBottom_toTopOf="@+id/search_location"
//        app:layout_constraintEnd_toEndOf="parent"
//        app:layout_constraintStart_toStartOf="parent"
//        app:layout_constraintTop_toBottomOf="@+id/imageView" />

package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.util.Log;
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
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.SearchAdapterLocation;
import edu.gatech.cs2340.donationtracker.R;

public class ViewLocationActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListModel model = ListModel.INSTANCE;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> expandableListList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private SearchAdapterLocation searchAdapter;
    SearchView editsearch;
    ArrayList<LocationItem> arraylist = new ArrayList<>();
    private ExpandableListView simpleExpandableListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("model. ",model.getItems().toString());


        setContentView(R.layout.activity_view_location);
        View recyclerView = findViewById(R.id.locationitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(ViewLocationActivity.this, expandableListList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);
        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
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
        simpleExpandableListView.setOnGroupExpandListener(new ExpandableListView.OnGroupExpandListener() {
            @Override
            public void onGroupExpand(int groupPosition) {
                if (prevExpandPosition[0] >= 0 && prevExpandPosition[0] != groupPosition) {
                    simpleExpandableListView.collapseGroup(prevExpandPosition[0]);
                }
                prevExpandPosition[0] = groupPosition;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = expandableListList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });
        //searchAdapter = new SearchAdapter(ViewLocationActivity.this, deptList);
        searchAdapter = new SearchAdapterLocation(ViewLocationActivity.this, arraylist);
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
    public void onCancelLocationPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        //overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    //load some initial data into out list
    private void loadData(){

        addProduct("Location Latitude and Longitude","Sort By Latitude");
        addProduct("Location Latitude and Longitude","Sort By Longitude");

        addProduct("Location Name","Sort Alphabetically");

        addProduct("Location Type","Sort By Drop-Off");
        addProduct("Location Type","Sort By Store");
        addProduct("Location Type","Sort By Warehouse");

        addProduct("Location Address","Sort Alphabetically");
        addProduct("Location Address","Sort By Closeness");

        addProduct("Location Phone Number","Sort Numerically");
        addProduct("Location Phone Number","Sort By Area Code");

    }

    //here we maintain our products in various departments
    private int addProduct(String department, String product){

        int groupPosition = 0;

        //check the hash map if the group already exists
        GroupInfo headerInfo = subjects.get(department);
        //add the group if doesn't exists
        if(headerInfo == null){
            headerInfo = new GroupInfo();
            headerInfo.setName(department);
            subjects.put(department, headerInfo);
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
        detailInfo.setName(product);
        productList.add(detailInfo);
        headerInfo.setProductList(productList);

        //find the group position inside the list
        groupPosition = expandableListList.indexOf(headerInfo);
        return groupPosition;
    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
        List<LocationItem> filteredLocations = model.getItems();
        if (filteredLocations.isEmpty()) {
            Toast.makeText(ViewLocationActivity.this, "Selected filter doesn't have donations.", Toast.LENGTH_SHORT).show();
        }
        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(filteredLocations));
    }

    public class SimpleItemRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleItemRecyclerViewAdapter.ViewHolder> {

        private final List<LocationItem> mValues;

        public SimpleItemRecyclerViewAdapter(List<LocationItem> items) {
            mValues = items;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.locationitem_list_content, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText("" + mValues.get(position).getKey());
            holder.mContentView.setText(mValues.get(position).getLocationName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), "Location Info", Toast.LENGTH_SHORT).show();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);

                    Bundle b = new Bundle();

                    String name = "" + mValues.get(position).getLocationName();
                    String type = "" + mValues.get(position).getType();
                    String address = mValues.get(position).getAddress();
                    String latitudeLongitude = mValues.get(position).getLatitude() + "/"
                            + "" + mValues.get(position).getLongitude();
                    String phoneNumber = mValues.get(position).getPhone();
                    String website = mValues.get(position).getWebsite();
                    b.putString("name", name);
                    b.putString("type", type);
                    b.putString("address", address);
                    b.putString("latitudeLongitude", latitudeLongitude);
                    b.putString("phoneNumber", phoneNumber);
                    b.putString("website", website);

                    intent.putExtras(b);
                    context.startActivity(intent);
                    //overridePendingTransition(R.anim.fadein, R.anim.fadeout);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mValues.size();
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            public final View mView;
            public final TextView mIdView;
            public final TextView mContentView;
            public LocationItem mItem;

            public ViewHolder(View view) {
                super(view);
                mView = view;
                mIdView = view.findViewById(R.id.id);
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
        //overridePendingTransition(R.anim.slideleft, R.anim.slideright);
    }
}
