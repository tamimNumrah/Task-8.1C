package com.tamim.subtask;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class RemoteAudioActivity extends AppCompatActivity {
    Button playButton;
    MediaPlayer mediaPlayer;
    String url = "https://file-examples.com/storage/fe59cbbb63645c19f9c3014/2017/11/file_example_MP3_5MG.mp3";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_audio);
        playButton = findViewById(R.id.playButton);
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playButton Pressed");
                mediaPlayer = new MediaPlayer();
                try {
                    mediaPlayer.setDataSource(url);
                    mediaPlayer.prepare();
                    mediaPlayer.start();
                }  catch (IOException e) {
                    System.out.println("prepare() failed");
                }
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
    }
}