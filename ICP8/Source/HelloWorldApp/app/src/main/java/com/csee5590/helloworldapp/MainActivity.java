package com.csee5590.helloworldapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView Info;
    private Button Login;
    private int counter = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name = findViewById(R.id.etName);
        Password = findViewById(R.id.etPassword);
        Info = findViewById(R.id.attemps);
        Login = findViewById(R.id.btnLogin);

        Info.setText("No of attempts remaining: 3");

        Button exitButton = (Button)findViewById(R.id.exitbutton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this,"Are you sure you want to quit?",Toast.LENGTH_LONG).show();
                finish();
                System.exit(0);

            }
        });

    }

    public void loginToApp(View view) {

        Toast.makeText(this,"Logging in .....",Toast.LENGTH_SHORT).show();
        String userName = Name.getText().toString();
        String userPassword = Password.getText().toString();

        if((userName.equals("Madhuri")) && (userPassword.equals("1234"))){
            Toast.makeText(this,"Logged In",Toast.LENGTH_LONG).show();
            Intent i = new Intent(MainActivity.this, MainActivity2.class);
            i.putExtra("key",userName);
            startActivity(i);

        }else{
            counter--;
            Toast.makeText(this,"No such user",Toast.LENGTH_LONG).show();
            Info.setText("No of attempts remaining: " + counter);

            if(counter == 0){
                Login.setEnabled(false);
            }
        }

    }

    public void signUp(View view) {
        String userName = Name.getText().toString();
        Toast.makeText(this,"Signing up the new user",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity.this, SignUpActivity.class);
        i.putExtra("key",userName);
        startActivity(i);




    }
}
