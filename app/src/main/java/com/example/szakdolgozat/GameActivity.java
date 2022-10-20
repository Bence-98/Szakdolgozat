package com.example.szakdolgozat;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.DisplayMetrics;

public class GameActivity extends Activity {

    private MediaPlayer backgroundMusic;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;


        backgroundMusic = MediaPlayer.create(GameActivity.this, R.raw.gamemusic);
        backgroundMusic.setLooping(true);


        backgroundMusic.start();



        setContentView(new GamePanel(this));
    }

    @Override
    protected void onPause () {
        super.onPause();
        backgroundMusic.pause();
    }


}