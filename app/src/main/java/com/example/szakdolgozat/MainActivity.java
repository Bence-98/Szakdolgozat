package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button playbtn = (Button) findViewById(R.id.playbtn);
        playbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGameActivity();
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

    public void openOptionsActivity(){
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
        //this.overridePendingTransition(0, 0);
    }

    public void openGameActivity(){
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        //this.overridePendingTransition(0, 0);
    }

}