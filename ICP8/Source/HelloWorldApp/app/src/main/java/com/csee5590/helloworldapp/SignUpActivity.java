package com.csee5590.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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
        Intent i = new Intent(SignUpActivity.this, MainActivity2.class);
        i.putExtra("key",userName);
        startActivity(i);

    }
}