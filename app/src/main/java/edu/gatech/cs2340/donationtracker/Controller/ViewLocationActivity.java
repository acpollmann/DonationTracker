//<Searc
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

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import edu.gatech.cs2340.donationtracker.Model.ChildInfo;
import edu.gatech.cs2340.donationtracker.Model.CustomAdapter;
import edu.gatech.cs2340.donationtracker.Model.GroupInfo;
import edu.gatech.cs2340.donationtracker.Model.SearchAdapter;
import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.ListModel;

public class ViewLocationActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {
    ListModel model = ListModel.INSTANCE;
    private LinkedHashMap<String, GroupInfo> subjects = new LinkedHashMap<String, GroupInfo>();
    private ArrayList<GroupInfo> deptList = new ArrayList<GroupInfo>();
    private CustomAdapter listAdapter;
    private SearchAdapter searchAdapter;
    private ExpandableListView simpleExpandableListView;
    public SearchView editsearch;
    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_location);
        View recyclerView = findViewById(R.id.locationitem_list);
        assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);
//        readSDFile();
        // add data for displaying in expandable list view
        loadData();

        //get reference of the ExpandableListView
        simpleExpandableListView = (ExpandableListView) findViewById(R.id.simpleExpandableListView);
        // create the adapter by passing your ArrayList data
        listAdapter = new CustomAdapter(ViewLocationActivity.this, deptList);
        // attach the adapter to the expandable list view
        simpleExpandableListView.setAdapter(listAdapter);

        //expand all the Groups
        expandAll();

        // setOnChildClickListener listener for child row click
        simpleExpandableListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //get the child info
                ChildInfo detailInfo = headerInfo.getProductList().get(childPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Clicked on :: " + headerInfo.getName()
                        + "/" + detailInfo.getName(), Toast.LENGTH_LONG).show();
                return false;
            }
        });
        // setOnGroupClickListener listener for group heading click
        simpleExpandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                //get the group header
                GroupInfo headerInfo = deptList.get(groupPosition);
                //display it or do something with it
                Toast.makeText(getBaseContext(), " Header is :: " + headerInfo.getName(),
                        Toast.LENGTH_LONG).show();

                return false;
            }
        });
        searchAdapter = new SearchAdapter(ViewLocationActivity.this, deptList);
        // Locate the EditText in listview_main.xml
//        editsearch = (SearchView) findViewById(R.id.searchView);
//        editsearch.setOnQueryTextListener(this);

//        if (findViewById(R.id.locationitem_details) != null) {
//            // The detail container view will be present only in the
//            // large-screen layouts (res/values-w900dp).
//            // If this view is present, then the
//            // activity should be in two-pane mode.
//            mTwoPane = true;
//        }
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
    }
    Intent intent = getIntent();

//    public void onLoadButtonPressed(View view) {
//        Log.v(MainActivity.TAG, "Pressed the load button");
//        try {
//            readSDFile();
//            Intent intent = new Intent();
//            startActivity(intent);
//        } catch (Exception e) {
//            String data = e.getMessage();
//        }
//    }

    public static final int NAME_POSITION = 0;



    //method to expand all groups
    private void expandAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.expandGroup(i);
        }
    }

    //method to collapse all groups
    private void collapseAll() {
        int count = listAdapter.getGroupCount();
        for (int i = 0; i < count; i++){
            simpleExpandableListView.collapseGroup(i);
        }
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
            deptList.add(headerInfo);
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
        groupPosition = deptList.indexOf(headerInfo);
        return groupPosition;
    }

//    private void readSDFile() {
//        //ListModel model = ListModel.INSTANCE;
//
//        try {
//            //Open a stream on the raw file
//            InputStream is = getResources().openRawResource(R.raw.locationdata);
//            //From here we probably should call a model method and pass the InputStream
//            //Wrap it in a BufferedReader so that we get the readLine() method
//            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
//
//            String line;
//            br.readLine(); //get rid of header line
//            while ((line = br.readLine()) != null) {
//                Log.d(MainActivity.TAG, line);
//                String[] tokens = line.split(",");
//                int key = Integer.parseInt(tokens[0]);
//                double latitude = Double.parseDouble(tokens[2]);
//                double longitude = Double.parseDouble(tokens[3]);
//                int zip = Integer.parseInt(tokens[7]);
//                if (model.findItemById(key) == null) {
//                    model.addItem(new LocationItem(key, tokens[1], latitude, longitude,
//                            tokens[4], tokens[5], tokens[6], zip,
//                            tokens[8], tokens[9], tokens[10]));
//                }
//            }
//            br.close();
//        } catch (IOException e) {
//            Log.e(MainActivity.TAG, "error reading assets", e);
//        }
//    }

    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {

        recyclerView.setAdapter(new SimpleItemRecyclerViewAdapter(ListModel.INSTANCE.getItems()));
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
            holder.mContentView.setText(mValues.get(position)._getLocationName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplication(), "Location Info", Toast.LENGTH_SHORT).show();
                    Context context = v.getContext();
                    Intent intent = new Intent(context, LocationDetailActivity.class);

                    Bundle b = new Bundle();

                    String name = "" + mValues.get(position)._getLocationName();
                    String address = "" + mValues.get(position)._getAddress();
                    String latitudeLongitude = "" + mValues.get(position)._getLatitude() + "/"
                            + "" + mValues.get(position)._getLongitude();
                    String phoneNumber = "" + mValues.get(position)._getPhone();
                    String website = mValues.get(position)._getWebsite();
                    b.putString("name", name);
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
                mIdView = (TextView) view.findViewById(R.id.id);
                mContentView = (TextView) view.findViewById(R.id.content);
            }

            @Override
            public String toString() {
                return super.toString() + " '" + mContentView.getText() + "'";
            }
        }
    }
}
