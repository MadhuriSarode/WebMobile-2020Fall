package com.example.listviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity1 extends AppCompatActivity {

    EditText name;
    EditText emailID;
    Switch sw;

    /**
     * Runs on the activity's start.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String value = extras.getString("key");
        }
        name = findViewById(R.id.editTextTextPersonName);               // Collects user's/order's name
        emailID = findViewById(R.id.editTextTextEmailAddress);          // Collects the email ID of the customer
        sw = findViewById(R.id.DeliveryOrPickUp);                       // Collects the delivery option chosen by the customer.

    }

    /**
     * Customer proceeds to order after providing required details
     * @param view
     */
    public void proceedToOrder(View view) {
        Toast.makeText(this,"Make your own pizza",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity1.this, MainActivity2.class);
        String userDetails = name.getText() + " ," + emailID.getText() + "," + sw.getTextOn();
        System.out.println("userDetails from mainactivity1 = " + userDetails);
        i.putExtra("userDetails" , userDetails);
        startActivity(i);                                               // All of the user details are passed onto next activity using intent
    }
}