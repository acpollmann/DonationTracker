package edu.gatech.cs2340.donationtracker.Controller;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import edu.gatech.cs2340.donationtracker.Model.LocationItem;

public class LocationDetailFragment {
//
}
/**
 //     * The fragment argument representing the item ID that this fragment
 //     * represents.
 //     */
//    public static final String ARG_ITEM_ID = "item_id";
//
//    /**
//     * The dummy content this fragment is presenting.
//     */
//    private LocationItem mItem;
//
//    /**
//     * Mandatory empty constructor for the fragment manager to instantiate the
//     * fragment (e.g. upon screen orientation changes).
//     */
//    public DataItemDetailFragment() {
//    }
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//        if (getArguments().containsKey(ARG_ITEM_ID)) {
//            // Load the dummy content specified by the fragment
//            // arguments. In a real-world scenario, use a Loader
//            // to load content from a content provider.
//            int item_id = getArguments().getInt(ARG_ITEM_ID);
//            Log.d("MYAPP", "Start details for: " + item_id);
//            mItem = SimpleModel.INSTANCE.findItemById(item_id);
//
//            Activity activity = this.getActivity();
//            CollapsingToolbarLayout appBarLayout = (CollapsingToolbarLayout) activity.findViewById(R.id.toolbar_layout);
//            if (appBarLayout != null) {
//                appBarLayout.setTitle(mItem.getName());
//            }
//        }
//    }
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.dataitem_detail, container, false);
//        Log.d("MYAPP", "Getting ready to set data");
//        // Show the dummy content as text in a TextView.
//        if (mItem != null) {
//            Log.d("MYAPP", "Getting ready to set id");
//            ((TextView) rootView.findViewById(R.id.id2)).setText("" + mItem.getId());
//            Log.d("MYAPP", "Getting ready to set name");
//            ((TextView) rootView.findViewById(R.id.name)).setText(mItem.getName());
//            ((TextView) rootView.findViewById(R.id.email)).setText(mItem.getEmail());
//            ((TextView) rootView.findViewById(R.id.fruit)).setText(mItem.getFruit());
//        }
//
//        return rootView;
//    }

//<Button
//        android:id="@+id/button"
//                android:layout_width="wrap_content"
//                android:layout_height="0dp"
//                android:text="Button"
//                android:onClick="onLoadButtonPressed"/>
