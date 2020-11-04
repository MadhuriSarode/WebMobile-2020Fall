package com.example.vijaya.earthquakeapp;

import android.content.Intent;
import android.os.StrictMode;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class FirstActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);


        //The exit button to gracefully exit the application
        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(FirstActivity.this,"Are you sure you want to quit?",Toast.LENGTH_LONG).show();
                android.os.SystemClock.sleep(2000);
                finish();
                System.exit(0);

            }
        });

    }

    /**
     * View detailed list of the quakes along with USGC map link
     * @param view
     */
    public void viewDetailedQuakeList(View view) {
        Toast.makeText(this,"List of quakes within last few days!!!!!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(FirstActivity.this, EarthquakeActivity.class);
        startActivity(i);
    }

    /**
     * SHows the list of just the locations of the quakes
     */
    public void showLocationList() {

        String URL = "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=4&limit=10";
        QueryUtils.fetchEarthquakeData2(URL);
        String allLocationsStr = QueryUtils.allLocations;
        Toast.makeText(this,"List of locations of the quakes within last few days!!!!!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(FirstActivity.this, LocationListActivity.class);
        i.putExtra("locationlist",allLocationsStr);
        startActivity(i);
    }

    /**
     * Approving netwrok permissions to execute thread ont he first activity
     * @param view
     */
    public void onViewCreated(View view)
    {
        int SDK_INT = android.os.Build.VERSION.SDK_INT;
        if (SDK_INT > 8)
        {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder()
                    .permitAll().build();
            StrictMode.setThreadPolicy(policy);
            showLocationList();
        }
    }

    /**
     * Displays the number of quakes reported at present time.
     * @param view
     */
    public void numOfQuakesReported(View view) {
        Snackbar.make(view, "Number of quakes reported = " +QueryUtils.count, Snackbar.LENGTH_SHORT).show();
    }

    /**
     * The maximum magnitude of the quake reported among the list.
     * @param view
     */
    public void maxMagQuake(View view) {
            double maxMagQuakeInt;
            double temp[] = QueryUtils.magnitude;
            Arrays.sort(temp);
            maxMagQuakeInt = temp[temp.length-1];
            Snackbar.make(view, "Maximum magnitude quake reported = " + maxMagQuakeInt, Snackbar.LENGTH_SHORT).show();
    }
}