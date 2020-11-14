package com.example.hotelmanagementapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class BookSuite extends AppCompatActivity {

    TextView nameOfItem;
    ImageButton prev;
    ImageButton next;
    ImageView suiteView;
    int j=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_suite);
        nameOfItem = findViewById(R.id.NameOfItem);
        prev = findViewById(R.id.prevButton);
        next = findViewById(R.id.nextButton);
        suiteView = findViewById(R.id.suiteView);
        int i=0;
        int a = getImage(i);
        suiteView.setImageResource(a);
    }


    /**
     * The topping's picture and name shown as the user naviagtes through the list of toppings
     * @param i
     * @return
     */
    public int getImage(int i)
    {
        switch (i)
        {
            case 0 :  { nameOfItem.setText("King"); return R.mipmap.suites1; }
            case 1 :  { nameOfItem.setText("Queen"); return R.mipmap.suites2; }
            case 2 :  { nameOfItem.setText("Double Bed"); return R.mipmap.suites3; }
        }
        return R.drawable.ic_launcher_background;
    }

    /**
     * Navigates to the previous topping image
     * @param view
     */
    public void PreviousSuite(View view) {
        if(j > 0 )
        {
            j--;
        }
        int a = getImage(j);
        suiteView.setImageResource(a);
    }

    /**
     * Navigates to the next topping image
     * @param view
     */
    public void nextSuite(View view) {
        if(j < 3 )
        {
            j++;
        }
        int a = getImage(j);
        suiteView.setImageResource(a);
    }

    /**
     * Adding the floor plan along with the quantity.
     * @param view
     */
    public void addFloorPlan(View view) {

        String currentItem = nameOfItem.getText().toString();
        Toast.makeText(this,"Selecting the floor plan " + currentItem,Toast.LENGTH_SHORT).show();
    }


    public void kidFriendlyInstructions(View view) {
    }

    public void petFriendlyInstructions(View view) {
    }
}