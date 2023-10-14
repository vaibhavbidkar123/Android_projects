package com.example.song_player;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    boolean lock1=false,lock2=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1=findViewById(R.id.button);
        Button btn2=findViewById(R.id.button2);

        MediaPlayer m2=MediaPlayer.create(getApplicationContext(),R.raw.song2);
        MediaPlayer m1=MediaPlayer.create(getApplicationContext(),R.raw.song1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m1.isPlaying()) {

                    m1.pause();
                }else {
                    m1.start();
                    if(m2.isPlaying())
                    m2.pause();
                }

            }

        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(m2.isPlaying()) {

                    m2.pause();
                }else {

                    m2.start();
                    if(m1.isPlaying())
                    m1.pause();
                }
            }

        });

    }
}