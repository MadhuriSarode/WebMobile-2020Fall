package com.example.calenderevent;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText title;
    EditText location;
    EditText description;
    EditText allDay;
    Button addEvent;
    CalendarView calendarView;
    Date date1;
    Calendar beginCal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        title = findViewById(R.id.title);
        location = findViewById(R.id.location);
        description = findViewById(R.id.description);
        addEvent = findViewById( R.id.button);
        allDay = findViewById(R.id.allDay);
        calendarView = findViewById(R.id.calendarView);
        textView = findViewById(R.id.textView);


       //Once a new date for an event is selected, it is recorded and populated in google calender.
        calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView view, int year, int month, int dayOfMonth) {
                String date = (month + 1) + "/" + dayOfMonth + "/" + year;
                Date date2 = new GregorianCalendar(year, month - 1, dayOfMonth).getTime();
                date1 = date2;
                beginCal = Calendar.getInstance();
                beginCal.set(year, month, dayOfMonth, 00, 00);
                textView.setText("Event being set for date : " + date);

            }
        });
        //Button onclick listener. Once the button 'Add event' is clicked, The data from calender view, the event details are routed to
        //Google calender application and populated in respected fields.
        //So, the event can be saved automatically.
      addEvent.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              if(!title.getText().toString().isEmpty() && !location.getText().toString().isEmpty() && !description.getText().toString().isEmpty())
              {
                  //Populating the google calender event from user page
                  Intent intent = new Intent(Intent.ACTION_INSERT);
                  intent.setData(CalendarContract.Events.CONTENT_URI);
                  intent.putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME ,beginCal.getTimeInMillis());
                  intent.putExtra(CalendarContract.Events.TITLE,title.getText().toString());
                  intent.putExtra(CalendarContract.Events.EVENT_LOCATION,location.getText().toString());
                  intent.putExtra(CalendarContract.Events.DESCRIPTION,description.getText().toString());
                  intent.putExtra(CalendarContract.Events.ALL_DAY,allDay.getText().toString());
                  intent.putExtra(Intent.EXTRA_EMAIL,"test@gmail.com");
                  if(intent.resolveActivity(getPackageManager()) != null)
                  {
                      startActivity(intent);                            // Started activity google calender
                  }else
                  {
                      Toast.makeText(MainActivity.this,"No app to support this" , Toast.LENGTH_SHORT).show();
                  }

              }
              else
              {
                  Toast.makeText(MainActivity.this,"PLease fill all the fileds" , Toast.LENGTH_SHORT).show();
              }
          }
      });




    }
}