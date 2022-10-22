package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class CharacterSelect extends AppCompatActivity {

    private ImageView black, blue, green, yellow;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_select);

        black = (ImageView) findViewById(R.id.black);
        blue = (ImageView) findViewById(R.id.blue);
        green = (ImageView) findViewById(R.id.green);
        yellow = (ImageView) findViewById(R.id.yellow);

        textView = (TextView) findViewById(R.id.textView);

        changeText();


        black.setOnClickListener(this::onClick);
        blue.setOnClickListener(this::onClick);
        green.setOnClickListener(this::onClick);
        yellow.setOnClickListener(this::onClick);

    }

    public void onClick(View v){
        switch (v.getId()){
            case R.id.black:
                Constants.CHARACTER_COLOR = 0;
                changeText();
                break;
            case R.id.blue:
                Constants.CHARACTER_COLOR = 1;
                changeText();
                break;
            case R.id.green:
                Constants.CHARACTER_COLOR = 2;
                changeText();
                break;
            case R.id.yellow:
                Constants.CHARACTER_COLOR = 3;
                changeText();
                break;
        }

    }

    public void changeText(){
        switch (Constants.CHARACTER_COLOR){
            case 0:
                textView.setTextColor(Color.BLACK);
                textView.setText("Black");
                break;
            case 1:
                textView.setTextColor(Color.BLUE);
                textView.setText("Blue");
                break;
            case 2:
                textView.setTextColor(Color.GREEN);
                textView.setText("Green");
                break;
            case 3:
                textView.setTextColor(Color.YELLOW);
                textView.setText("Yellow");
                break;
        }
    }


}