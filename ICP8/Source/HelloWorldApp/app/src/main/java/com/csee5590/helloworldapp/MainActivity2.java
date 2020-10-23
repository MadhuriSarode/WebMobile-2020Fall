package com.csee5590.helloworldapp;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.Date;

public class MainActivity2 extends AppCompatActivity {

    private TextView loggedInName;
    private TextView dateTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        loggedInName = findViewById(R.id.editTextTextPersonName);
        dateTime = findViewById(R.id.DateTime);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mail us at mgs638", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {

            String value = extras.getString("key");
            loggedInName.setText(value);
        }


        Button exitButton = (Button)findViewById(R.id.logOutButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity2.this,"Are you sure you want to quit?",Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);

            }
        });

        Date currentTime = Calendar.getInstance().getTime();
        dateTime.setText( currentTime.toString());


    }
}