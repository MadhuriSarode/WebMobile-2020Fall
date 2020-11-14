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

public class SelectionOfServices extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selection_of_services);

        String[] myArray = {"Book Suite","Accessability","View by map"};
        List<String> fixedLenghtList = Arrays.asList(myArray);

//create object of listview holding all the locations list
        ListView listView=(ListView)findViewById(R.id.listOfServices);

//create ArrayList of String
        final ArrayList<String> arrayList=new ArrayList<String>(fixedLenghtList);

//Create array Adapter and fill in the data in it, of the location list
        ArrayAdapter arrayAdapter=new ArrayAdapter(this,android.R.layout.simple_list_item_1,arrayList);

//assign adapter to listview
        listView.setAdapter(arrayAdapter);

//add listener to listview

        Toast.makeText(SelectionOfServices.this,"Click on any servies",Toast.LENGTH_SHORT).show();

        //Displaying the list of locations of quakes along with the link attached to it
        //which directs the user to view more details.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = "clicked item:" + i + " " + arrayList.get(i) + " Following the  link for more details";
                Snackbar.make(view, str, Snackbar.LENGTH_SHORT).show();


                if(i==0)
                {
                    Snackbar.make(view, "Moving onto booking a suite", Snackbar.LENGTH_SHORT).show();
                    Intent j = new Intent(SelectionOfServices.this, BookSuite.class);
                    startActivity(j);
                }

                if(i==1)
                {
                    Snackbar.make(view, "Accessability", Snackbar.LENGTH_SHORT).show();
                    Intent j = new Intent(SelectionOfServices.this, Accesability.class);
                    startActivity(j);
                }

                if(i==2)
                {
                    Snackbar.make(view, "View by map", Snackbar.LENGTH_SHORT).show();
                    String location= "https://www.google.com/maps/dir//Hilton+Garden+Inn+Overland+Park,+5800+College+Blvd,+Overland+Park,+KS+66211/@38.9280724,-94.7228134,12z/data=!4m8!4m7!1m0!1m5!1m1!1s0x87c0ea2f74c7b6cf:0xeac3403b557d7230!2m2!1d-94.6527734!2d38.9280933?hl=en-US";
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(location));
                    startActivity(browserIntent);

                }


            }
        });




    }
}