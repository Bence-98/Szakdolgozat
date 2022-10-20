package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //private MediaPlayer backgroundMusic;
    Intent svc = new Intent(this,BgMusic.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



/*        backgroundMusic = MediaPlayer.create(MainActivity.this, R.raw.backgroundmusic);
        backgroundMusic.setLooping(true);
        backgroundMusic.start();*/



        Button playbtn = (Button) findViewById(R.id.playbtn);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity();
            }
        });

        Button charselectbtn = (Button) findViewById(R.id.charselectbtn);
        charselectbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openCharacterSelectActivity();
            }
        });


        Button optionsbtn = (Button) findViewById(R.id.optionsbtn);
        optionsbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOptionsActivity();
            }
        });

        Button quitbtn = (Button) findViewById(R.id.quitbtn);
        quitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                System.exit(0);
            }
        });


    }


/*
    @Override
    protected void onPause () {
        super.onPause();
        backgroundMusic.pause();
    }

    @Override
    protected void onStart(){
        super.onStart();
        backgroundMusic.start();
    }
*/
    protected void onStart(){
        super.onStart();
        startService(svc);

    }


    private void openCharacterSelectActivity() {
        Intent intent = new Intent(this, CharacterSelect.class);
        startActivity(intent);
    }

    public void openOptionsActivity() {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
        //this.overridePendingTransition(0, 0);
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        //this.overridePendingTransition(0, 0);
    }

}