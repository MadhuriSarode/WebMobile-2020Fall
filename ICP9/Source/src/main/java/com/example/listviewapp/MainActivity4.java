package com.example.listviewapp;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Process;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity4 extends AppCompatActivity {

    TextView finalBillPayment;
    String str[];
    String totalBill;
    String orderSummary;
    ProgressBar pb;
    int pregressStatus = 0;
    TextView status;

    Handler mhandler = new Handler();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);
        finalBillPayment = findViewById(R.id.finalBillPayment123);
        pb = findViewById(R.id.progressBar);
        pb.setVisibility(ProgressBar.VISIBLE);
        status = findViewById(R.id.pizzaready);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           String userDetails = extras.getString("userDetails");
            totalBill = extras.getString("totalbill");
            orderSummary = extras.getString("ordersummary");
            orderSummary = orderSummary +" Total = $"+ totalBill;
             str = userDetails.split(",");
             String data = "Name : "+str[0] + "\n" + "Mail ID : "+ str[1] +"\n"+ "Delivery option "+str[2];
            finalBillPayment.setText(data);      //getting value here
        }


        //Exit button for application
        Button exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                finish();
                System.exit(0);
            }
        });


        //The progress bar running time thread. Currently for 500ms
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (pregressStatus  < 100 )
                {
                    pregressStatus++;
                    android.os.SystemClock.sleep(500);

                    mhandler.post(new Runnable() {
                        @Override
                        public void run() {
                            pb.setProgress(pregressStatus);
                        }
                    });
                }

                mhandler.post(new Runnable() {
                    @Override
                    public void run() {
                        status.setVisibility(View.VISIBLE);
                    }
                });
            }
        }).start();

    }


    /**
     * This method is called when the order now button is clicked.
     * An alert dialog box appears asking if receipt should be mailed to the cutomer.
     * @param view
     */
    public void sendMail(View view) {
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Your pizza order is received");
        alert.setMessage("Want to send your receipt in mail?");

        //If the customer chooses 'yes' for receipt, navigating to mail option where order summary and billing details along with customer's
        //details are used in gmail.
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (str[1].length() != 0 ) {
                    Intent emailIntent = new Intent(Intent.ACTION_SEND);
                    // The intent does not have a URI, so declare the "text/plain" MIME type
                    emailIntent.setType("plain/text");
                    // Customer's email ID
                    emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {str[1]});
                    // Adding Subject
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Order Summary for " + str[0]);
                    // Adding the Order Summary Text along with billing details
                    emailIntent.putExtra(Intent.EXTRA_TEXT, orderSummary);
                    startActivity(Intent.createChooser(emailIntent, ""));
                }
            }
        });
        //If the customer says no for receipt, then no eamil is sent.
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(MainActivity4.this, "Pizza is being ready", Toast.LENGTH_LONG).show();
            }
        });
        alert.create().show();
    }


}