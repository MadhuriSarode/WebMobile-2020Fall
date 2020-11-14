package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Accesability extends AppCompatActivity {

    TextView featuresChosen;
    Button back;
    Button viewAllFeatures;
    Button button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accesability);
        featuresChosen = findViewById(R.id.featuresChosen);
        back = findViewById(R.id.back);
        viewAllFeatures = findViewById(R.id.viewAllFeatures);
        button3= findViewById(R.id.button3);


    }

    public void goBackToServicesPage(View view) {

        String str = featuresChosen.getText().toString();
        Intent i = new Intent(Accesability.this, SelectionOfServices.class);
        i.putExtra("Accesability_features_chosen",str);
        startActivity(i);
    }

    public void getListOfFeatures(View view) {
        Intent i = new Intent(Accesability.this, AccesabilityList.class);
        startActivity(i);
    }

    public void showFeatures(View view) {
        featuresChosen.setText(AccesabilityList.selectedFeatures);
    }
}