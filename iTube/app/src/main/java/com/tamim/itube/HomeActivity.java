package com.tamim.itube;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HomeActivity extends AppCompatActivity {
    TextView titleTextView;
    EditText editTextYoutubeURL;
    Button playButton;
    Button addToPlayListButton;
    Button myPlayListButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        titleTextView = findViewById(R.id.titleTextView);
        editTextYoutubeURL = findViewById(R.id.editTextYoutubeURL);
        playButton = findViewById(R.id.playButton);
        addToPlayListButton = findViewById(R.id.addToPlayListButton);
        myPlayListButton = findViewById(R.id.myPlayListButton);

        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("playButton Pressed");
            }
        });
        addToPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("addToPlayListButton Pressed");
            }
        });
        myPlayListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("myPlayListButton Pressed");
            }
        });

    }
}