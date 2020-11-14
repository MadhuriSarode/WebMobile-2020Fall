package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AccesabilityList extends AppCompatActivity  {
    private ArrayAdapter arrayAdapter;
    public static String selectedFeatures;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesability_list);

        String locationlist="Access from site arrival points to the hotel entrance, including pedestrian paths to travel and curb cuts where needed.,\n" +
                "Accessible parking for vans and standard automobiles, including proper striping and signage,\n" +
                "Accessible access aisles.,\n" +
                "An accessible entrance with sufficient door width and level landings.,\n" +
                "An accessible registration counter.,\n" +
                "Accessible guest rooms.,\n" +
                "Accessible guest room bathrooms, with features for use by individuals with disabilities, including grab bars, accessible toilets, roll-in or tub showers, accessible controls, and other features.,\n" +
                "Auxiliary aids and services.,\n" +
                "If applicable, accessible pool lift.";
        String[] myArray = locationlist.split(",");
        List<String> fixedLenghtList = Arrays.asList(myArray);

//create object of listview holding all the locations list
        ListView listView=(ListView)findViewById(R.id.locationList);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<String>(fixedLenghtList);

//Create array Adapter and fill in the data in it, of the location list
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview

        Toast.makeText(AccesabilityList.this,"Click on any feature to select it",Toast.LENGTH_SHORT).show();

        //Displaying the list of locations of quakes along with the link attached to it
        //which directs the user to view more details.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = arrayList.get(i) + ": Adding it to your list";
                Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();
                String loc = arrayList.get(i);
                selectedFeatures += loc;

            }
        });

    }
}