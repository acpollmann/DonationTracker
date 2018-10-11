package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.CollapsingToolbarLayout;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import edu.gatech.cs2340.donationtracker.R;
import edu.gatech.cs2340.donationtracker.Model.LocationItem;
import edu.gatech.cs2340.donationtracker.Model.ListModel;



public class LocationDetailFragment extends Fragment {
    /**
     * The fragment argument representing the item ID that this fragment
     * represents.
     */
    public static final String ARG_ITEM_ID = "item_id";

    /**
     * The adapter for the recycle view list of students
     */
    //private SimpleStudentRecyclerViewAdapter adapter;


    /**
     * The dummy content this fragment is presenting.
     */
    private LocationItem mItem;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public LocationDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            // Load the dummy content specified by the fragment
            // arguments. In a real-world scenario, use a Loader
            // to load content from a content provider.
            int item_id = getArguments().getInt(ARG_ITEM_ID);
            Log.d("MYAPP", "Start details for: " + item_id);
            mItem = ListModel.INSTANCE.findItemById(item_id);

            Activity activity = this.getActivity();
            //CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem._getLocationName());
//            }
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.locationitem_detail, container, false);
        Log.d("MYAPP", "Getting ready to set data");
        // Show the dummy content as text in a TextView.
        if (mItem != null) {
            Log.d("MYAPP", "Getting ready to set id");
            ((TextView) rootView.findViewById(R.id.id2)).setText("" + mItem.getKey());
            Log.d("MYAPP", "Getting ready to set name");
            ((TextView) rootView.findViewById(R.id.location_name)).setText(mItem._getLocationName());
        }
        return rootView;
    }

    /**
     * Set up an adapter and hook it to the provided view
     *
     * @param recyclerView  the view that needs this adapter
     */
//    private void setupRecyclerView(@NonNull RecyclerView recyclerView) {
//        adapter = new SimpleStudentRecyclerViewAdapter(mCourse.getStudents());
//        Log.d("Adapter", adapter.toString());
//        recyclerView.setAdapter(adapter);
//    }
//
//    /**
//     * This inner class is our custom adapter.  It takes our basic model information and
//     * converts it to the correct layout for this view.
//     *
//     * In this case, we are just mapping the toString of the Student object to a text field.
//     */
//    public class SimpleStudentRecyclerViewAdapter
//            extends RecyclerView.Adapter<SimpleStudentRecyclerViewAdapter.ViewHolder> {
//
//        /**
//         * Collection of the items to be shown in this list.
//         */
//        private final List<LocationItem> mValues;
//
//        /**
//         * set the items to be used by the adapter
//         * @param items the list of items to be displayed in the recycler view
//         */
//        public SimpleStudentRecyclerViewAdapter(List<LocationItem> items) {
//            mValues = items;
//        }
//
//        @Override
//        public SimpleStudentRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            /*
//              This sets up the view for each individual item in the recycler display
//              To edit the actual layout, we would look at: res/layout/course_list_content.xml
//              If you look at the example file, you will see it currently just 2 TextView elements
//             */
//            View view = LayoutInflater.from(parent.getContext())
//                    .inflate(R.layout.locationitem_detail, parent, false);
//            return new SimpleStudentRecyclerViewAdapter.ViewHolder(view);
//        }
//
//        @Override
//        public void onBindViewHolder(final SimpleStudentRecyclerViewAdapter.ViewHolder holder, int position) {
//            /*
//            This is where we have to bind each data element in the list (given by position parameter)
//            to an element in the view (which is one of our two TextView widgets
//             */
//            //start by getting the element at the correct position
//            holder.mStudent = mValues.get(position);
//            Log.d("Adapter", "student: " + holder.mStudent);
//            /*
//              Now we bind the data to the widgets.  In this case, pretty simple, put the id in one
//              textview and the string rep of a course in the other.
//             */
//            holder.mIdView.setText("" + mValues.get(position).getId());
//            holder.mContentView.setText(mValues.get(position).toString());
//
//            /*
//             * set up a listener to handle if the user clicks on this list item, what should happen?
//             */
//            holder.mView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    //on a phone, we need to change windows to the detail view
//                    Context context = v.getContext();
//                    //create our new intent with the new screen (activity)
//                    Intent intent = new Intent(context, EditStudentActivity.class);
//                        /*
//                            pass along the selected student we can retrieve the correct data in
//                            the next window
//                         */
//                    intent.putExtra(CourseDetailFragment.ARG_STUDENT_ID, holder.mStudent);
//
//                    //now just display the new window
//                    context.startActivity(intent);
//
//                }
//            });
//        }
//
//        @Override
//        public int getItemCount() {
//            return mValues.size();
//        }
//
//        /**
//         * This inner class represents a ViewHolder which provides us a way to cache information
//         * about the binding between the model element (in this case a Course) and the widgets in
//         * the list view (in this case the two TextView)
//         */
//
//        public class ViewHolder extends RecyclerView.ViewHolder {
//            public final View mView;
//            public final TextView mKeyView;
//            public final TextView mContentView;
//            public LocationItem mLocation;
//
//            public ViewHolder(View view) {
//                super(view);
//                mView = view;
//                mKeyView = (TextView) view.findViewById(R.id.student_id);
//                Log.d("Holder", mKeyView.toString());
//                mContentView = (TextView) view.findViewById(R.id.student_details);
//            }
//
//            @Override
//            public String toString() {
//                return super.toString() + " '" + mContentView.getText() + "'";
//            }
//        }
//    }
}

