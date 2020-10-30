package com.example.listviewapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    /**
     * Runs on the activity's start.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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


    /**
     * Option to order as guest.
     * @param view
     */
    public void startAsGuest(View view) {
        Toast.makeText(this,"We Welcome our guest to order!!!!!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, MainActivity1.class);
        i.putExtra("key","Guest");
        startActivity(i);
    }
}