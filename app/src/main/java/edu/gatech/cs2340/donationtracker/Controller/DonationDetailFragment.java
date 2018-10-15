package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.donationtracker.Model.Donation;
import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.R;


/**
 * A fragment representing a single Course detail screen.
 *
 * Basically this displays details of students that are in a particular donation
 * that was selected from the main screen.
 *
 */

public class DonationDetailFragment extends Fragment {
        /**
         * The fragment arguments representing the  ID's that this fragment
         * represents.  Used to pass keys into other activities through Bundle/Intent
         */
        public static final String ARG_DONATION_ID = "donation_id";

        /**
         * The donation that this detail view is for.
         */
        private Donation mDonation;

        /**
         * The adapter for the recycle view list of students
         */
        private SimpleStudentRecyclerViewAdapter adapter;

        @Override
        public void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

            //Check if we got a valid course passed to us
            if (getArguments().containsKey(ARG_DONATION_ID)) {
                // Get the id from the intent arguments (bundle) and
                //ask the model to give us the course object
                ListModel model = ListModel.INSTANCE;
                // mCourse = model.getCourseById(getArguments().getInt(ARG_COURSE_ID));
                mDonation = model.getCurrentDonation();
                Log.d("CourseDetailFragment", "Passing over donation: " + mDonation);
                Log.d("CourseDetailFragment", "Got name: " + mDonation.getName());

                Activity activity = this.getActivity();

//                CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//                if (appBarLayout != null) {
//                    appBarLayout.setTitle(mDonation.toString());
//                }
            }

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.donation_detail, container, false);

            //Step 1.  Setup the recycler view by getting it from our layout in the main window
            View recyclerView = rootView.findViewById(R.id.donation_list);
            assert recyclerView != null;
            //Step 2.  Hook up the adapter to the view
            setupRecyclerView((RecyclerView) recyclerView);

            return rootView;
        }

        @Override
        public void onResume() {
            super.onResume();
            adapter.notifyDataSetChanged();
        }

        /**
         * Set up an adapter and hook it to the provided view
         *
         * @param recyclerView  the view that needs this adapter
         */
        private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
            adapter = new SimpleStudentRecyclerViewAdapter(ListModel.INSTANCE.getDonations());
            Log.d("Adapter", adapter.toString());
            recyclerView.setAdapter(adapter);
        }

        /**
         * This inner class is our custom adapter.  It takes our basic model information and
         * converts it to the correct layout for this view.
         *
         * In this case, we are just mapping the toString of the Student object to a text field.
         */
        public class SimpleStudentRecyclerViewAdapter
                extends RecyclerView.Adapter<SimpleStudentRecyclerViewAdapter.ViewHolder> {

            /**
             * Collection of the items to be shown in this list.
             */
            private final List<Donation> mValues;

            /**
             * set the items to be used by the adapter
             * @param items the list of items to be displayed in the recycler view
             */
            public SimpleStudentRecyclerViewAdapter(List<Donation> items) {
                mValues = items;
            }

            @Override
            public SimpleStudentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            /*
              This sets up the view for each individual item in the recycler display
              To edit the actual layout, we would look at: res/layout/course_list_content.xml
              If you look at the example file, you will see it currently just 2 TextView elements
             */
                View view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.donation_detail, parent, false);
                return new SimpleStudentRecyclerViewAdapter.ViewHolder(view);
            }

            @Override
            public void onBindViewHolder(final SimpleStudentRecyclerViewAdapter.ViewHolder holder, int position) {
            /*
            This is where we have to bind each data element in the list (given by position parameter)
            to an element in the view (which is one of our two TextView widgets
             */
                //start by getting the element at the correct position
                holder.mDonation = mValues.get(position);
                Log.d("Adapter", "student: " + holder.mDonation);
            /*
              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
              textview and the string rep of a course in the other.
             */
                holder.mIdView.setText("" + mValues.get(position).getKey());
                holder.mContentView.setText(mValues.get(position).toString());

//            /*
//             * set up a listener to handle if the user clicks on this list item, what should happen?
//             */
//                holder.mView.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        //on a phone, we need to change windows to the detail view
//                        Context context = v.getContext();
//                        //create our new intent with the new screen (activity)
//                        Intent intent = new Intent(context, EditStudentActivity.class);
//                        /*
//                            pass along the selected student we can retrieve the correct data in
//                            the next window
//                         */
//                        // intent.putExtra(DonationDetailFragment.ARG_DONATION_ID, holder.mDonation);
//
//                        //now just display the new window
//                        context.startActivity(intent);
//
//                    }
//                });
            }

            @Override
            public int getItemCount() {
                return mValues.size();
            }

            /**
             * This inner class represents a ViewHolder which provides us a way to cache information
             * about the binding between the model element (in this case a Course) and the widgets in
             * the list view (in this case the two TextView)
             */

            public class ViewHolder extends RecyclerView.ViewHolder {
                public final View mView;
                public final TextView mIdView;
                public final TextView mContentView;
                public Donation mDonation;

                public ViewHolder(View view) {
                    super(view);
                    mView = view;
                    mIdView = (TextView) view.findViewById(R.id.donation_id);
                    Log.d("Holder", mIdView.toString());
                    mContentView = (TextView) view.findViewById(R.id.donation_details);
                }

                @Override
                public String toString() {
                    return super.toString() + " '" + mContentView.getText() + "'";
                }
            }
        }
    }
