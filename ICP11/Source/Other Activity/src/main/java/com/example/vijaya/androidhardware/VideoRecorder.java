package com.example.vijaya.androidhardware;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.vijaya.androidhardware.R;

public class VideoRecorder extends AppCompatActivity {

    private Uri videoUri = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video_recorder);
    }

    public void recordVideo(View view) {

        try {
            //Create a video intent with Media store action
            Intent videoIntent = new Intent(MediaStore.ACTION_VIDEO_CAPTURE);
            if (videoIntent.resolveActivity(getPackageManager()) != null) {
                startActivityForResult(videoIntent, 101);
            }
        }catch (Exception e)
        {
            System.out.println("exception = " );e.printStackTrace();
        }
    }

    public void showVideo(View view) {

        //Redirect to play the recorded video to next activity
        Intent playIntent = new Intent(this,VideoPlayActivity.class);
        //Video URI is passed as a string to play the video
        playIntent.putExtra("videoUri",videoUri.toString());
        startActivity(playIntent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == 101 && resultCode ==RESULT_OK)
        {
            //Get the vide URI after the video is recorded
            videoUri = data.getData();
        }
    }
}