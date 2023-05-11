package com.tamim.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.UUID;

public class HomeActivity extends AppCompatActivity {
    TextView titleTextView;
    EditText editTextYoutubeURL;
    Button playButton;
    Button addToPlayListButton;
    Button myPlayListButton;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        titleTextView = findViewById(R.id.titleTextView);
        editTextYoutubeURL = findViewById(R.id.editTextYoutubeURL);
        playButton = findViewById(R.id.playButton);
        addToPlayListButton = findViewById(R.id.addToPlayListButton);
        myPlayListButton = findViewById(R.id.myPlayListButton);
        db = new DatabaseHelper(this);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playButton Pressed");
                playButtonPressHandle();
            }
        });
        addToPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("addToPlayListButton Pressed");
                addToPlaylist();
            }
        });
        myPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("myPlayListButton Pressed");
                Intent intent = new Intent(HomeActivity.this, PlaylistActivity.class);
                startActivity(intent);
            }
        });
    }

    private void playButtonPressHandle() {
        String url = editTextYoutubeURL.getText().toString();
        if (url.matches("")) {
            showToast("You did not enter any URL");
            return;
        }
        Intent intent = new Intent(HomeActivity.this, PlayVideoActivity.class);
        intent.putExtra("url", url);
        startActivity(intent);
    }

    private void addToPlaylist() {
        String url = editTextYoutubeURL.getText().toString();
        if (url.matches("")) {
            showToast("You did not enter any URL");
            return;
        }
        PlayItem playItem = new PlayItem(UUID.randomUUID().toString(), url);
        Boolean success = db.insertPlayItem(playItem);
        if (success) {
            showToast("Added to playlist");
            editTextYoutubeURL.setText("");
        } else {
            showToast("Add to playlist failed");
        }
    }
    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}