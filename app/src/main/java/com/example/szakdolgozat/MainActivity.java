package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp = getSharedPreferences("Settings", MODE_PRIVATE);

        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("BgMusic", true);
        editor.putBoolean("WeaponSound", true);
        editor.apply();

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

    protected void onStart() {
        super.onStart();
        //startService(svc);
    }

    private void openCharacterSelectActivity() {
        Intent intent = new Intent(this, CharacterSelect.class);
        startActivity(intent);
    }

    public void openOptionsActivity() {
        Intent intent = new Intent(this, OptionsActivity.class);
        startActivity(intent);
    }

    public void openGameActivity() {
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
    }
}