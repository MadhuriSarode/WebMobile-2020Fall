package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class SelectHotels extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_hotels);
    }


//1.
   //  increment decremnt the hotel's images with names , selecet that location
    // check in, user other details(adults, kids, elders etc),price range,handicap,coupons, check out,
    // Go screen button

  //  2.
    //Once the user selects a location, hit an API, get array of hotels.
    //Diaply in images the hotels along with price (Hotel ABC - $300)
    //Show features button shows the features in a text view below it.
   //Book my hotel button . Put all the user selected details in intent variable, so that it is available to next activity.

}