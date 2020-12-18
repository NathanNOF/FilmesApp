package com.nathan.filmesapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.VideoView;

import com.nathan.filmesapp.R;

public class PlayerActivity extends AppCompatActivity {

    private VideoView mainVideoView;
    private ImageView playbtn;
    private TextView currentTimer;
    private TextView durationTimer;
    private ProgressBar currentProgress;
    private ProgressBar bufferProgress;


    private Boolean isPlaying;

    private Uri videoUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        isPlaying = false;

        mainVideoView = (VideoView) findViewById(R.id. mainVideoView);
        playbtn = (ImageView) findViewById(R.id.playbtn);
        currentProgress = (ProgressBar) findViewById(R.id.videoProgress);
        currentTimer = (TextView) findViewById(R.id.currentTimer);
        durationTimer = (TextView) findViewById(R.id.durationTimer);
        bufferProgress = (ProgressBar) findViewById(R.id.bufferProgress);

        videoUri = Uri.parse("https://firebasestorage.googleapis.com/v0/b/filmesapp-86192.appspot.com/o/videos%2Fvideo01?alt=media&token=8562d8da-6a17-4356-bafe-c76fe7d02689");

         mainVideoView.setVideoURI(videoUri);
         mainVideoView.requestFocus();


         mainVideoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
             @Override
             public boolean onInfo(MediaPlayer mediaPlayer, int i, int i1) {

                 if (i == mediaPlayer.MEDIA_INFO_AUDIO_NOT_PLAYING ){

                     bufferProgress.setVisibility(View.VISIBLE);
                 }else if (i == mediaPlayer.MEDIA_INFO_AUDIO_NOT_PLAYING){

                     bufferProgress.setVisibility(View.INVISIBLE);

                 }

                 return false;
             }
         });


         mainVideoView.start();
         isPlaying =true;
         playbtn.setImageResource(R.drawable.ic_pause_black_24dp);




         playbtn.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 if (isPlaying){

                     mainVideoView.pause();
                     isPlaying = false;
                     playbtn.setImageResource(R.drawable.ic_pause_black_24dp);
                 }else{
                     mainVideoView.start();
                     isPlaying = true;
                     playbtn.setImageResource(R.drawable.ic_pause_black_24dp);
                 }
             }
         });
    }

}
