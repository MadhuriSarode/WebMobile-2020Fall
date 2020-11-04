package com.example.vijaya.earthquakeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LocationListActivity extends AppCompatActivity {

    ListView locationListView;
    private ArrayAdapter arrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_list);

        //Getting the customer's details from the previous activity.
        Bundle extras = getIntent().getExtras();
        String locationlist="";
        if (extras != null) {
            locationlist = extras.getString("locationlist");
        }
        String[] myArray = locationlist.split(",");
        List<String> fixedLenghtList = Arrays.asList(myArray);

//create object of listview
        ListView listView=(ListView)findViewById(R.id.locationList);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<String>(fixedLenghtList);

//Create Adapter
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview

        Toast.makeText(LocationListActivity.this,"Click on any location to view the more detailed data about each location",Toast.LENGTH_SHORT).show();

        //Displaying the list of locations of quakes along with the link attached to it
        //which directs the user to view more details.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = "clicked item:" + i + " " + arrayList.get(i) + " Following the  link for more details";
                Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();
                String loc = arrayList.get(i);
                String link = QueryUtils.locLink.get(loc);
                if (link != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                    startActivity(browserIntent);
            }
            }
        });

    }
}