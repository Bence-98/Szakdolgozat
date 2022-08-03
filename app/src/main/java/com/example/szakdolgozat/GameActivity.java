package com.example.szakdolgozat;

import android.app.Activity;
import android.os.Bundle;
import android.text.style.DynamicDrawableSpan;
import android.util.DisplayMetrics;
import android.view.Display;

public class GameActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WITDH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        setContentView(new GamePanel(this));
    }
}