package com.tamim.subtask;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

public class RemoteVideoActivity extends AppCompatActivity {

    VideoView videoView;
    String url = "https://file-examples.com/storage/fe59cbbb63645c19f9c3014/2017/04/file_example_MP4_1280_10MG.mp4";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remote_video);
        videoView = findViewById(R.id.videoView);
        Uri uri = Uri.parse(url);
        videoView.setVideoURI(uri);
        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        mediaController.setMediaPlayer(videoView);
        videoView.setMediaController(mediaController);
        videoView.start();
    }
}