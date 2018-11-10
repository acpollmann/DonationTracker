package edu.gatech.cs2340.donationtracker.Controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;
import java.util.Objects;

import edu.gatech.cs2340.donationtracker.Model.ListModel;
import edu.gatech.cs2340.donationtracker.Model.Location;
import edu.gatech.cs2340.donationtracker.R;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        Objects.requireNonNull(mapFragment).getMapAsync(this);
    }

    public void onBackButtonPressed(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slideright, R.anim.slideleft);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        //save the map instance returned from Google
        /* holds the map object returned from Google */
        GoogleMap mMap = googleMap;

        mMap.getUiSettings().setZoomControlsEnabled(true);

        //get the data to display
        List<Location> locationList = ListModel.getInstance().getLocations();

        //iterate through the list and add a pin for each element in the model
        for (Location location : locationList) {
            Log.d("Location added", location.getLatitude() + ", "  + location.getLongitude());
            LatLng loc = new LatLng(location.getLatitude(), location.getLongitude());
            mMap.addMarker(new MarkerOptions().position(loc).title(location.getLocationName())
                    .snippet(location.getPhone()));
        }
        Double gatechLat = 33.7756;
        Double gatechLong = -84.3963;
        Float initialZoom = 10.0f;
        LatLng gatech = new LatLng(gatechLat, gatechLong);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(gatech, initialZoom));

        //Use a custom layout for the pin data
        mMap.setInfoWindowAdapter(new CustomInfoWindowAdapter());
    }

    /**
     * This class implements a custom layout for the pin
     */
    class CustomInfoWindowAdapter implements GoogleMap.InfoWindowAdapter {

        private final View myContentsView;

        /**
         * Make the adapter
         */
        CustomInfoWindowAdapter(){
            // hook up the custom layout view in res/custom_map_pin_layout.xml
            myContentsView = getLayoutInflater().inflate(R.layout.custom_map_pin_layout, null);
        }

        @Override
        public View getInfoContents(Marker marker) {

            TextView tvTitle = myContentsView.findViewById(R.id.title);
            tvTitle.setText(marker.getTitle());
            TextView tvSnippet = ((TextView)myContentsView.findViewById(R.id.snippet));
            tvSnippet.setText(marker.getSnippet());

            return myContentsView;
        }

        @Override
        public View getInfoWindow(Marker marker) {
            return null;
        }

    }
}
