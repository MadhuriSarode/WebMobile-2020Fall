package com.example.currentplacedetailsonmap;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button locationButton;
    TextView savedAddresses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

         locationButton = findViewById(R.id.locationButton);
         savedAddresses = findViewById(R.id.savedAddresses);
        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Are you sure you want to quit?",Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);

            }
        });

    }

    public void moveOnToCurrentLoc(View view)
    {
        Toast.makeText(this,"We Welcome you to view your location!!!!!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, MapsActivityCurrentPlace.class);
        startActivity(i);

    }

    public void getCurrentLocationAddress(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Current Location Address");
        alert.setMessage(MapsActivityCurrentPlace.locationStr);

        //If the customer chooses 'yes' for receipt, navigating to mail option where order summary and billing details along with customer's
        //details are used in gmail.
        alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        //If the customer says no for receipt, then no eamil is sent.
        alert.setNegativeButton("Save", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                savedAddresses.setText(MapsActivityCurrentPlace.locationStr);
            }
        });
        alert.create().show();

    }
}