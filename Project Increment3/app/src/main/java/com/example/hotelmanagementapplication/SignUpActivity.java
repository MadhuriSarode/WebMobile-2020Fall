package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {

    private EditText newUser_Name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        newUser_Name = findViewById(R.id.newUserName);
    }

    public void signUpDeatils(View view) {
        String userName = newUser_Name.getText().toString();
        Toast.makeText(this,"Logged In",Toast.LENGTH_LONG).show();
        Intent i = new Intent(SignUpActivity.this, WelcomeGuest.class);
        i.putExtra("loggedInGuestName",userName);
        startActivity(i);

    }
}