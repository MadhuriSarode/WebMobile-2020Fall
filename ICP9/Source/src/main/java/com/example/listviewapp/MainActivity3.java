package com.example.listviewapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity3 extends AppCompatActivity {

    TextView finalPizza;
    TextView billing;
    String userDetails;
    TextView totalP;
    int total;
    EditText customTip;
    String orderSummary;
    int quantity;
    int total_one;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        finalPizza = findViewById(R.id.Items_in_cart);
        billing = findViewById(R.id.billingview);
        totalP = findViewById(R.id.Total);
        customTip = findViewById(R.id.customTip);


        //Many parameters such as user details, order details and billing details are set in intent to pass onto next activity
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            orderSummary = extras.getString("key");
            String bill = extras.getString("bill");
             total = extras.getInt("Total");
            total_one = extras.getInt("total_one");
             userDetails = extras.getString("userDetails");
            quantity = extras.getInt("quantity");
            finalPizza.setText(orderSummary);
            bill = bill + "\n Price of one pizza=" + total_one +  "\n Quantity = "+quantity;
            billing.setText(bill);
            totalP.setText("TotalPrice = "+total);
        }
    }

    /**
     * Customer pays the bill and also adds tips.
     * @param view
     */
    public void PayTheBill(View view) {


        //Alert dialog box to check if customer wants to move on to payment or has to make changes in pizza

            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Moving on to payment");
            alert.setMessage("Do you want to make any changes to your pizza?");

            //Positive response. Making changes with pizza before moving forward
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity3.this, "Make changes before you proceed", Toast.LENGTH_LONG).show();
                }
            });
            //Moving forward to payment.
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    Intent i = new Intent(MainActivity3.this, MainActivity4.class);
                    i.putExtra("userDetails", userDetails);
                    i.putExtra("ordersummary",orderSummary);
                    i.putExtra("totalbill",total+"");
                    startActivity(i);
                }
            });
            alert.create().show();
    }

    /**
     * Adding custom tip amount to the total bill amount.
     * @param view
     */
    public void addTipCustom(View view) {

                int tip =  Integer.parseInt(String.valueOf(customTip.getText()));
                System.out.println("after tip = " + tip);
                tip = tip + total;
                totalP.setText("TotalPrice = "+tip);
    }

    /**
     * Adding 15% tip to the total bill amount.
     * @param view
     */
    public void addTip(View view) {
        total = (total + (15 * total)/100 );
        totalP.setText("TotalPrice = "+total);
    }
}