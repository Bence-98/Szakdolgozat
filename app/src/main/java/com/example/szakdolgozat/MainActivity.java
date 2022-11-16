package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sp = getSharedPreferences("Settings", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("BgMusic", true);
        editor.putBoolean("WeaponSound", true);
        editor.apply();

        Button playbtn = findViewById(R.id.playbtn);
        playbtn.setOnClickListener(view -> openGameActivity());

        Button charselectbtn = findViewById(R.id.charselectbtn);
        charselectbtn.setOnClickListener(view -> openCharacterSelectActivity());

        Button optionsbtn = findViewById(R.id.optionsbtn);
        optionsbtn.setOnClickListener(view -> openOptionsActivity());

        Button quitbtn = findViewById(R.id.quitbtn);
        quitbtn.setOnClickListener(view -> {
            finish();
            System.exit(0);
        });
    }

    protected void onStart() {
        super.onStart();
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