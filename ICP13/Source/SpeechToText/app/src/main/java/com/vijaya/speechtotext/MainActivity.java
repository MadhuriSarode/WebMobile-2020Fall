package com.vijaya.speechtotext;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Date;
import java.util.TimeZone;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_SPEECH_INPUT = 100;
    private TextView mVoiceInputTv;
    private ImageView imageView;
    ImageView m;
    private ImageButton mSpeakBtn;
    TextToSpeech tts;
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    TextView patient;
    String name1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        patient = findViewById(R.id.patient);

        // Initializing Preferences, Editor
        preferences = getSharedPreferences("namePrefs",0);
        editor = preferences.edit();

        imageView = findViewById(R.id.imageView);
        m = findViewById(R.id.m);
        mVoiceInputTv = (TextView) findViewById(R.id.voiceInput);
        mSpeakBtn = (ImageButton) findViewById(R.id.btnSpeak);
        mSpeakBtn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                startVoiceInput();
            }
        });

        // Say Hello First on Page Load
        tts = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status != TextToSpeech.ERROR) {
                    // Setting the Locale.
                    tts.setLanguage(Locale.US);
                    tts.speak("Hello", TextToSpeech.QUEUE_FLUSH, null);
                    mVoiceInputTv.setText(Html.fromHtml("<p style=\"color:purple;\">Speaker : Hello !!</p>"));
                }
            }
        });

    }

    private void startVoiceInput() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Hello, How can I help you?");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {

        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {
                    // Fethcing the result
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(result != null && result.size() > 0) {
                        mVoiceInputTv.append(Html.fromHtml("<p style=\"color:green;\">User : "+result.get(0)+"</p>"));
                        // If user says hello, Ask for User's name & show the Greeting Text with users name.
                        if(result.get(0).equalsIgnoreCase("hello") || result.get(0).equalsIgnoreCase("hi")) {
                            tts.speak("What is your name", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : What is your name ?</p>"));
                        }else if(result.get(0).contains("name")){
                            // Set the patient's name in the text filed of patient's namee
                            String name = result.get(0).substring(result.get(0).lastIndexOf(' ') + 1);
                            name1 = name;
                            // Setting into Editor
                            editor.putString("name", name).apply();
                            patient.setText("Patient being diagnoised :" + name);
                            tts.speak("Hello, "+name,
                                    TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : Hello, "+name+"</p>"));
                        }else if(result.get(0).contains("not feeling well")){
                            tts.speak("I can understand. Please tell your symptoms in short",
                                    TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:red;\">Speaker : I can understand. Please tell your symptoms in short</p>"));
                        }else if(result.get(0).contains("thank you")){
                            tts.speak("Thank you too "+preferences.getString("name","")+", Take care.", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : Thank you too "+preferences.getString("name","")+", Take care.</p>"));
                        }else if(result.get(0).contains("day")){
                            Date date = new Date();
                            // Choose time zone in which you want to interpret your Date
                            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("Europe/Paris"));
                            cal.setTime(date);
                            int day = cal.get(Calendar.DAY_OF_MONTH);
                            tts.speak("Today is : "+day, TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : Today is : "+day+"</p>"));
                        }else if(result.get(0).contains("time")){
                            // Speaking the Time for the User
                            SimpleDateFormat sdfDate =new SimpleDateFormat("HH:mm");//dd/MM/yyyy
                            Date now = new Date();
                            String[] strDate = sdfDate.format(now).split(":");
                            if(strDate[1].contains("00"))strDate[1] = "o'clock";
                            tts.speak("The time is : "+sdfDate.format(now), TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : The time is : "+sdfDate.format(now)+"</p>"));
                        }else if(result.get(0).contains("medicine")) {
                            tts.speak("I think you have a fever. Please take this medicine.",
                                    TextToSpeech.QUEUE_FLUSH, null);
                            imageView.setImageResource(R.mipmap.fever);
                            m.setImageResource(R.mipmap.t);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : I think you have a fever. Please take this medicine.</p>"));
                        }else if(result.get(0).contains("done")) {
                            tts.speak("ok, terminating your session" + name1, TextToSpeech.QUEUE_FLUSH, null);

                            finish();
                            finish();
                        }
                        else {
                            tts.speak("Sorry, I cant help you with that", TextToSpeech.QUEUE_FLUSH, null);
                            mVoiceInputTv.append(Html.fromHtml("<p style=\"color:purple;\">Speaker : Sorry, I cant help you with that</p>"));
                        }
                    }
                }
                break;
            }

        }
    }
}