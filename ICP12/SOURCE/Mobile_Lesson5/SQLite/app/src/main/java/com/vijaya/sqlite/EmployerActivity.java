package com.vijaya.sqlite;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.vijaya.sqlite.databinding.ActivityEmployerBinding;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmployerActivity extends AppCompatActivity {

    private static final String TAG = "EmployerActivity";
    private ActivityEmployerBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_employer);

        binding.recycleView.setLayoutManager(new LinearLayoutManager(this));

        binding.saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveToDB();
            }
        });

        binding.searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                readFromDB();
            }
        });

        binding.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteFromDB();
            }
        });

        binding.updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateNameDB();
            }
        });

        binding.updateButtonDesc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDescDB();
            }
        });


    }

    private void saveToDB() {
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, binding.nameEditText.getText().toString());
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, binding.descEditText.getText().toString());

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    binding.foundedEditText.getText().toString()));
            long date = calendar.getTimeInMillis();
            values.put(SampleDBContract.Employer.COLUMN_FOUNDED_DATE, date);
        } catch (Exception e) {
            Log.e(TAG, "Error", e);
            Toast.makeText(this, "Date is in the wrong format", Toast.LENGTH_LONG).show();
            return;
        }
        long newRowId = database.insert(SampleDBContract.Employer.TABLE_NAME, null, values);

        Toast.makeText(this, "The new Row Id is " + newRowId, Toast.LENGTH_LONG).show();
    }

    private void readFromDB() {
        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();
        long date = 0;

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime((new SimpleDateFormat("dd/MM/yyyy")).parse(
                    binding.foundedEditText.getText().toString()));
            date = calendar.getTimeInMillis();
        } catch (Exception e) {
        }

        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();

        String[] projection = {
                SampleDBContract.Employer._ID,
                SampleDBContract.Employer.COLUMN_NAME,
                SampleDBContract.Employer.COLUMN_DESCRIPTION,
                SampleDBContract.Employer.COLUMN_FOUNDED_DATE
        };

        String selection =
                SampleDBContract.Employer.COLUMN_NAME + " like ? and " +
                        SampleDBContract.Employer.COLUMN_FOUNDED_DATE + " > ? and " +
                        SampleDBContract.Employer.COLUMN_DESCRIPTION + " like ?";

        String[] selectionArgs = {"%" + name + "%", date + "", "%" + desc + "%"};

        Cursor cursor = database.query(
                SampleDBContract.Employer.TABLE_NAME,     // The table to query
                projection,                               // The columns to return
                selection,                                // The columns for the WHERE clause
                selectionArgs,                            // The values for the WHERE clause
                null,                             // don't group the rows
                null,                              // don't filter by row groups
                null                              // don't sort
        );

        binding.recycleView.setAdapter(new SampleRecyclerViewCursorAdapter(this, cursor));
    }


    private void deleteFromDB(){
        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();
        Toast.makeText(EmployerActivity.this, "The record for deletion is  " + name, Toast.LENGTH_LONG).show();

        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();

        String[] selectionArgs = {"%" + name + "%", "%" + desc + "%"};
        Cursor cursor = database.rawQuery(SampleDBContract.SELECT_EMPLOYER_WITH_EMPLOYER, selectionArgs);
        int id=0;
        if (cursor != null && cursor.moveToFirst()){ //make sure you got results, and move to first row
            do{
                int mID = cursor.getInt(0); //column 0 for the current row
                id= mID;
            } while (cursor.moveToNext()); //move to next row in the query result

        }
        database.delete(SampleDBContract.Employer.TABLE_NAME, "_id="+id, null);
        Log.i("Information ", Integer.toString(id));
//        database.delete(SampleDBContract.Employee.TABLE_NAME,   "COLUMN_FIRSTNAME =?" , selectionArgs);
        Toast.makeText(EmployerActivity.this, "The record has been deleted for " + name + " and the ID number id " + id, Toast.LENGTH_LONG).show();

    }


    private void updateDescDB(){

        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();

        //Get an SQL DB instance
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();

        //Arguments for querying the DB
        String[] names = {"%" + name + "%", "%"};
        //Using cursor since the DB can return more than one row for the search criteria
        Cursor cursor = database.rawQuery(SampleDBContract.SELECT_EMPLOYER_WITH_EMPLOYER, names);

        int id=0;
        if (cursor != null && cursor.moveToFirst()){ //Move to first row in the results after all the results are fetched
            do{
                int mID = cursor.getInt(0); //Iterate column by column for each row
                id= mID;
            } while (cursor.moveToNext()); //move to next row in the query result

        }
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, name);
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, desc);
        database.update(SampleDBContract.Employer.TABLE_NAME, values, "_id="+id, null);
        Log.i("THIS IS ID",Integer.toString(id));
        Toast.makeText(EmployerActivity.this, "The record has been updated for " + name, Toast.LENGTH_LONG).show();
        readFromDB();
    }

    private void updateNameDB(){

        String name = binding.nameEditText.getText().toString();
        String desc = binding.descEditText.getText().toString();

        //Get an SQL DB instance
        SQLiteDatabase database = new SampleDBSQLiteHelper(this).getReadableDatabase();

        //Arguments for querying the DB
        String[] names = {"%", "%" + desc + "%"};
        //Using cursor since the DB can return more than one row for the search criteria
        Cursor cursor = database.rawQuery(SampleDBContract.SELECT_EMPLOYER_WITH_EMPLOYER, names);

        int id=0;
        if (cursor != null && cursor.moveToFirst()){ //Move to first row in the results after all the results are fetched
            do{
                int mID = cursor.getInt(0); //Iterate column by column for each row
                id= mID;
            } while (cursor.moveToNext()); //move to next row in the query result

        }
        ContentValues values = new ContentValues();
        values.put(SampleDBContract.Employer.COLUMN_NAME, name);
        values.put(SampleDBContract.Employer.COLUMN_DESCRIPTION, desc);
        database.update(SampleDBContract.Employer.TABLE_NAME, values, "_id="+id, null);
        Log.i("THIS IS ID",Integer.toString(id));
        Toast.makeText(EmployerActivity.this, "The record has been updated for " + name, Toast.LENGTH_LONG).show();
        readFromDB();
    }




}