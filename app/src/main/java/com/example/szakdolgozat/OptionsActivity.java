package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.widget.CheckBox;
import android.widget.TextView;

public class OptionsActivity extends AppCompatActivity {
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        sp = getSharedPreferences("Settings", MODE_PRIVATE);

        setContentView(R.layout.activity_options);

        CheckBox bgCheckB = findViewById(R.id.checkBox);
        CheckBox wpnCheckB = findViewById(R.id.checkBox2);

        bgCheckB.setChecked(sp.getBoolean("BgMusic", true));
        wpnCheckB.setChecked(sp.getBoolean("WeaponSound", true));

        TextView save = findViewById(R.id.textView);
        TextView close = findViewById(R.id.textView2);

        save.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sp.edit();
            editor.putBoolean("BgMusic", bgCheckB.isChecked());
            editor.putBoolean("WeaponSound", wpnCheckB.isChecked());
            editor.apply();
            finish();
        });
        close.setOnClickListener(view -> finish());
    }
}