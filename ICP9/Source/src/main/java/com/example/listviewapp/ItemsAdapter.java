package com.example.listviewapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemsAdapter extends BaseAdapter {

    LayoutInflater mInflator;
    String[] items;
    String[] desc;
    String[] price;

    public ItemsAdapter(Context c, String[] i, String[] d, String[] p)
    {
        items = i;
        desc = d;
        price = p;
        mInflator = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int position) {
        return items[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        View v = mInflator.inflate(R.layout.my_listview_details_r,null);
        TextView nameTextView =  v.findViewById(R.id.Name);
        TextView descTextView = v.findViewById(R.id.Desc);
        TextView priceTextView = v.findViewById(R.id.Price);

        String name = items[i];
        String descp = desc[i];
        String pricee = price[i];

        nameTextView.setText(name);
        descTextView.setText(descp);
        priceTextView.setText(pricee);

        return v;
    }
}
