package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;


        setContentView(R.layout.activity_options);


        TextView textView = (TextView) findViewById(R.id.szelesseg);
        textView.setText("Szelesseg: " + String.valueOf(Constants.SCREEN_WIDTH));

        TextView textView2 = (TextView) findViewById(R.id.magassag);
        textView2.setText("Magassag: " + String.valueOf(Constants.SCREEN_HEIGHT));
    }
}