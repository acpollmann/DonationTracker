package edu.gatech.cs2340.donationtracker.Controller;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.List;

import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.ListModel;

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

    public void onLoadButtonPressed(View view) {
        Log.v(MainActivity.TAG, "Pressed the load button");
        readSDFile();
        Intent intent = new Intent();
        startActivity(intent);
    }

    public static final int NAME_POSITION = 0;

    private void readSDFile() {
        ListModel model = ListModel.INSTANCE;

        try {
            //Open a stream on the raw file
            InputStream is = getResources().openRawResource(R.raw.locationdata);
            //From here we probably should call a model method and pass the InputStream
            //Wrap it in a BufferedReader so that we get the readLine() method
            BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));

            String line;
            br.readLine(); //get rid of header line
            while ((line = br.readLine()) != null) {
                Log.d(MainActivity.TAG, line);
                String[] tokens = line.split(",");
                int key = Integer.parseInt(tokens[0]);
                double latitude = Integer.parseInt(tokens[2]);
                double longitude = Integer.parseInt(tokens[3]);
                int zip = Integer.parseInt(tokens[7]);
                model.addItem(new LocationItem(key, tokens[1], latitude, longitude,
                                               tokens[4], tokens[5], tokens[6],zip,
                                               tokens[8], tokens[9], tokens[10]));
            }
            br.close();
        } catch (IOException e) {
            Log.e(MainActivity.TAG, "error reading assets", e);
        }

    }

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
        public void onBindViewHolder(final ViewHolder holder, int position) {
            holder.mItem = mValues.get(position);
            holder.mIdView.setText("" + mValues.get(position).getKey());
            holder.mContentView.setText(mValues.get(position)._getLocationName());

            holder.mView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                        Context context = v.getContext();
//                        Intent intent = new Intent(context, LocationDetailActivity.class);
//                        Log.d("MYAPP", "Switch to detailed view for item: " + holder.mItem.getKey());
//                        //intent.putExtra(DataItemDetailFragment.ARG_ITEM_ID, holder.mItem.getId());
//
//                        context.startActivity(intent);
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
