package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
//import support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Filter;
import android.widget.TextView;
import android.widget.ArrayAdapter;
import android.widget.Filterable;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.R;

public class ViewDonationsActivity extends AppCompatActivity {

    private Spinner categorySearchSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_donations);

        categorySearchSpinner = findViewById(R.id.category);

        View recyclerView = findViewById(R.id.donation_list);
         assert recyclerView != null;
        setupRecyclerView((RecyclerView) recyclerView);

        /*
          Set up the adapter to display the allowable categories in the spinner
         */
        final ArrayAdapter<LocationItem> categorySearchAdapter = new ArrayAdapter(this,
                android.R.layout.simple_spinner_item, Donation.searchLegalCategories);
        categorySearchAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        categorySearchSpinner.setAdapter(categorySearchAdapter);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView the view that needs this adapter
     */
    private void setupRecyclerView(RecyclerView recyclerView) {
        ListModel model = ListModel.INSTANCE;
        recyclerView.setAdapter(new SimpleDonationRecyclerViewAdapter(model.getDonations()));
//        SimpleDonationRecyclerViewAdapter adapter = new SimpleDonationRecyclerViewAdapter(model.getDonations());
//        adapter.getFilter().filter(categorySearchSpinner.getSelectedItem().toString());
    }

    /**
     * This inner class is our custom adapter.  It takes our basic model information and
     * converts it to the correct layout for this view.
     * <p>
     * In this case, we are just mapping the toString of the Course object to a text field.
     */
    public class SimpleDonationRecyclerViewAdapter
            extends RecyclerView.Adapter<SimpleDonationRecyclerViewAdapter.ViewHolder> implements Filterable{

        /**
         * Collection of the items to be shown in this list.
         */
        private List<Donation> mDonations;
        private SearchFilter filter;

        /**
         * set the items to be used by the adapter
         *
         * @param items the list of items to be displayed in the recycler view
         */
        public SimpleDonationRecyclerViewAdapter(List<Donation> items) {
            mDonations = items;
        }

        @Override
        public Filter getFilter(){
            ListModel model = ListModel.INSTANCE;
            if (filter == null) {
                filter = new SearchFilter(this, model.getDonations());
            }
            return (Filter)filter;
        }

        private class SearchFilter extends Filter {
            private final SimpleDonationRecyclerViewAdapter adapter;
            private final List<Donation> originalList;
            private final List<Donation> filteredList;

            private SearchFilter(SimpleDonationRecyclerViewAdapter adapter, List<Donation> originalList){
                super();
                this.adapter = adapter;
                this.originalList = new LinkedList<>(originalList);
                this.filteredList = new ArrayList<>();
            }

            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                filteredList.clear();
                final FilterResults results = new FilterResults();

                if((charSequence.toString()).equals("All")) {
                    filteredList.addAll(originalList);
                } else {
                    final String filterPattern = charSequence.toString().toLowerCase().trim();
                    for (Donation item: originalList) {
                        if (item.getCategory().contains(filterPattern)) {
                            filteredList.add(item);
                        }
                    }
                }
                results.values = filteredList;
                results.count = filteredList.size();
                return results;
            }

            @SuppressWarnings("unchecked")
            @Override
            public void publishResults(CharSequence charSequence, FilterResults filterResults) {
                Object additions = (Object)filterResults.values;
                mDonations = filteredList;
                adapter.notifyDataSetChanged();
            }
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

            final ListModel model = ListModel.INSTANCE;
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
                    //on a phone, we need to change windows to the detail view
//                    Context context = v.getContext();
                    //create our new intent with the new screen (activity)
 //                   Intent intent = new Intent(context, DonationDetailActivity.class);
                        /*
                            pass along the id of the course so we can retrieve the correct data in
                            the next window
                         */
//                    intent.putExtra(DonationDetailFragment.ARG_DONATION_ID, holder.mDonation.getKey());

//                    model.setCurrentDonation(holder.mDonation);

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
