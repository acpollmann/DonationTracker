package edu.gatech.cs2340.donationtracker.controller;

        import android.content.Intent;
        import android.os.Bundle;
        import android.support.v7.app.AppCompatActivity;
        import android.view.View;
        import android.widget.Button;
        import android.widget.TextView;

        import edu.gatech.cs2340.donationtracker.R;

public class MainActivity extends AppCompatActivity {

    private TextView mTextMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextMessage = (TextView) findViewById(R.id.message);
    }

    public void onLogoutPressed(View view) {
        Intent intent = new Intent(this, WelcomeScreenActivity.class);
        startActivity(intent);
    }
    public void onViewLocationButtonPressed(View view) {
        Button viewLocationButton = (Button) findViewById(R.id.view_location_bubble);
        viewLocationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, ViewLocationActivity.class));
            }
        });
    }
}
