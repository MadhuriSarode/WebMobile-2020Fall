package com.example.listviewapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class MainActivity2 extends AppCompatActivity {
    ImageView im;
    Button back;
    Button addToCart;
    TextView nameOfItem;
    Spinner spinner;
    Spinner pizzaSize;
    int j=0;
    String cheese;
     Map< String , Integer> billing = new HashMap<>();
     LinkedHashMap<String,Integer> priceMap = new LinkedHashMap<String,Integer>();
     Map< String , String> arr = new HashMap<>();
    String userDetails;
    int totalPrice;
    int quantity=0;

    /**
     * Runs on the activity's start.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        im = findViewById(R.id.imageView);
        spinner = (Spinner)findViewById(R.id.spinner1);
        pizzaSize = findViewById(R.id.PizzaSize);
        back = findViewById(R.id.back);
        addToCart = findViewById(R.id.add_to_cart);
        nameOfItem = findViewById(R.id.NameOfItem);


        //Image gallery layout of the cheese options
        LinearLayout gallery;
        gallery = findViewById(R.id.gallery);
            LayoutInflater inflator = LayoutInflater.from(this);
            View view = inflator.inflate(R.layout.items,gallery,false);
            ImageView imageView = view.findViewById(R.id.imageView3);
            gallery.addView(view);


        //Getting the customer's details from the previous activity.
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            userDetails = extras.getString("userDetails");
        }

        int i=10;
        int a = getImage(i);
        im.setImageResource(a);

        //Building the map of prices vs items
        buildBillingMap();
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
            case 0 :  { nameOfItem.setText("Basil"); return R.drawable.basil; }
            case 1 :  { nameOfItem.setText("BellPeppers"); return R.drawable.bellpeppers; }
            case 2 :  { nameOfItem.setText("Mushrooms"); return R.drawable.mushroom; }
            case 3 :  { nameOfItem.setText("Onions"); return R.drawable.onion; }
            case 4 :  { nameOfItem.setText("Pepperoni"); return R.drawable.pepperoni; }
            case 5 :  { nameOfItem.setText("Spinach"); return R.drawable.spinach; }
            case 10 :  { nameOfItem.setText(" Select Toppings"); return R.drawable.imagepizza; }


        }
        return R.drawable.ic_launcher_background;
    }

    /**
     * The back button which takes you to previous activity.
     * @param view
     */
    public void goBack(View view) {
        finish();
        finish();
    }


    /**
     * Add the selected items to cart along with their specification.
     * @param view
     */
    public void addToCart(View view) {

        String currentItem = nameOfItem.getText().toString();
        String quantitySelected = (String) spinner.getSelectedItem();

        if(quantitySelected.equalsIgnoreCase("delete"))
        {
            arr.remove(currentItem);
        }
        else {
            if(arr.containsKey(currentItem))
            {
                String inMap = arr.get(currentItem);
                String temp = quantitySelected + inMap;
                arr.put(currentItem, inMap);
            }
            else {
                arr.put(currentItem, (quantitySelected));
            }
        }

        displayCartContents();                  // Display the cart contents/ order summary
    }

    /**
     * Show the customer's order and selected items for pizza, along with the price breakdown for each item.
     */
    public void displayCartContents()
    {
        String allItems = "";
        for (String name: arr.keySet()){
            String key = name;
            allItems += key + " " + arr.get(name) + "\n";
        }
        if(allItems.equalsIgnoreCase(""))
        {
            allItems = "Cart is empty";
        }

        totalPrice=billing.get("total");
        billing.remove("total");
        String billingDetails = showBillingList();


        // Sending the order summary and billing details to next activity, so that it can be used for email.
        Toast.makeText(this,"Your pizza looks like this!!!!!",Toast.LENGTH_LONG).show();
        Intent i = new Intent(MainActivity2.this, MainActivity3.class);
        i.putExtra("key",allItems);
        i.putExtra("bill",billingDetails);
        i.putExtra("userDetails" , userDetails);
        i.putExtra("total_one",totalPrice);
        totalPrice = totalPrice * quantity;
        i.putExtra("Total",totalPrice);
        i.putExtra("quantity",quantity);
        startActivity(i);

    }

    /**
     * Builds the billing list according to the items
     * @return
     */
    public String showBillingList()
    {
        String allItems = "";
        for(String name: billing.keySet()){
            String key = name;
            allItems += key + "   $" + billing.get(name) + "\n";
        }
        return allItems;
    }

    /**
     * Navigates to the previous topping image
     * @param view
     */
    public void PreviousTopping(View view) {
        if(j > 0 )
        {
            j--;
        }
        int a = getImage(j);
        im.setImageResource(a);
    }

    /**
     * Navigates to the next topping image
     * @param view
     */
    public void nextTopping(View view) {
        if(j < 5 )
        {
            j++;
        }
        int a = getImage(j);
        im.setImageResource(a);
    }

    /**
     * Radio button group for selecting cheese. Once an option is selected, it is added to cart and added in billing details.
     * @param view
     */
    public void onRadioButtonClicked(View view) {

        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.cheddar:
                if (checked)
                    cheese = "Cheddar";
                    break;
            case R.id.Mozarella:
                if (checked)
                    cheese = "Mozarella";
                    break;
            case R.id.pepperjack:
                if (checked)
                    cheese = "PepperJack";
                    break;
            case R.id.vegan:
                if (checked)
                    cheese = "Vegan";
                    break;
        }
        arr.put("cheese",cheese);
        billing(cheese.toLowerCase(),"");
        Toast.makeText(this,"Adding Cheese " + cheese,Toast.LENGTH_SHORT).show();
    }


    /**
     * Adding the toppings along with the quantity.
     * @param view
     */
    public void addTopping(View view) {

        String currentItem = nameOfItem.getText().toString();
        String quantitySelected = (String) spinner.getSelectedItem();

        //deleting the topping if customer does it.
        if(quantitySelected.equalsIgnoreCase("delete"))
        {
            arr.remove(currentItem);                                    //Remove the deleted item from cart array
            Toast.makeText(this,"Deleting topping " + currentItem,Toast.LENGTH_SHORT).show();
            billing(currentItem.toLowerCase(),"delete");
        }
        else {
            if(arr.containsKey(currentItem))
            {
                String inMap = arr.get(currentItem);
                String temp = quantitySelected + inMap;
                arr.put(currentItem, temp);                 // Adding the selected toppings in the final cart array
            }
            else {
                arr.put(currentItem, (quantitySelected));
            }
            billing(currentItem.toLowerCase(),"");
            Toast.makeText(this,"Adding topping" + currentItem + " of " + quantitySelected,Toast.LENGTH_SHORT).show();
        }


    }

    /**
     * Select pizza size and add it to cart array
     * @param view
     */
    public void addPizzaSize(View view) {

        String pizzaSizeStr = (String) pizzaSize.getSelectedItem();
        arr.put("Pizza Size " , pizzaSizeStr);
        Toast.makeText(this,"Pizza size will be " + pizzaSizeStr,Toast.LENGTH_SHORT).show();
        billing(pizzaSizeStr.toLowerCase(),"");
    }


    /**
     * Extract the items and their cost from strings.xml file and build it as a map to extract it later.
     */
    public void buildBillingMap()
    {
        String[] keys = this.getResources().getStringArray(R.array.items_billed);
        String[] values = this.getResources().getStringArray(R.array.price);
        for (int i = 0; i < Math.min(keys.length, values.length); ++i) {
            priceMap.put(keys[i], Integer.parseInt(values[i]));
        }
        billing.put("total",0);
    }

    /**
     *  Put a price on the item selected and add it in cart. If the item is getting deleted, the price of the item is removed from the billing list.
     * @param item
     * @param delete
     */
    public void billing(String item,String delete)
    {
        if(delete.equalsIgnoreCase("delete"))
        {
            billing.remove(item);
        }
        int price = priceMap.get(item);
        billing.put(item,price);
        int totalPrice = billing.get("total");
        totalPrice = totalPrice + price;
        billing.put("total",totalPrice);

    }


    /**
     * This method increments the quantity of coffee cups by one
     *
     * @param view on passes the view that we are working with to the method
     */

    public void increment(View view) {
        if (quantity < 10) {
            quantity = quantity + 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select less than one hundred cups of coffee");
            Context context = getApplicationContext();
            String lowerLimitToast = getString(R.string.too_much);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, lowerLimitToast, duration);
            toast.show();
            return;
        }
    }

    /**
     * This method decrements the quantity of coffee cups by one
     *
     * @param view passes on the view that we are working with to the method
     */
    public void decrement(View view) {
        if (quantity > 1) {
            quantity = quantity - 1;
            display(quantity);
        } else {
            Log.i("MainActivity", "Please select atleast one cup of coffee");
            Context context = getApplicationContext();
            String upperLimitToast = getString(R.string.too_less);
            int duration = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(context, upperLimitToast, duration);
            toast.show();
            return;
        }
    }


    /**
     * This method displays the given quantity value on the screen.
     */
    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view1);
        quantityTextView.setText("" + number);
    }

}