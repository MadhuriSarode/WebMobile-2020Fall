package com.example.hotelmanagementapplication;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loginButton = findViewById(R.id.LogIn);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Mail us at mgs638", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        //The exit button to gracefully exit the application
        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Are you sure you want to quit?",Toast.LENGTH_LONG).show();
                android.os.SystemClock.sleep(2000);
                finish();
                System.exit(0);

            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void moveOnToLogin(View view)
    {
        Toast.makeText(this,"Moving onto Login activity",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(i);

    }

    public void moveToSignUpDeatils(View view)
    {

        Toast.makeText(this,"Moving onto Sign up activity",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(i);
    }

    public void learnMoreAboutOurApp(View view)
    {
        //Add a link to video which  like    https://www.youtube.com/watch?v=lk7P8VYWR7c

        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=lk7P8VYWR7c"));
        startActivity(browserIntent);

    }



}