package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class OptionsActivity extends AppCompatActivity {

    //   StringBuilder textt = new StringBuilder();


/*    private List<String> lvlCoords = new ArrayList();
    private String getCoords(){
        InputStream is = getResources().openRawResource(R.raw.coords);
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        int lineNumber = 0;
        try {
        while ( (line = reader.readLine()) != null ) {
            if (lineNumber == 1)
                lvlcoords = line.split(",");
            lineNumber++;
        }
        }catch (IOException e) {e.printStackTrace();}
        return lvlCoords;
    }*/


/*    private String getCoords(){
        String kell = "";
        File file = new File("coords.txt");
        ArrayList<String> headerLines = new ArrayList();
        kell = "Hiba";
        if (file.exists())
            kell="van";
        else kell = "nincs";
        try {

            BufferedReader br = new BufferedReader(new FileReader(file));

            String line;

            int lineCount = 0;
            while ((line = br.readLine()) != null) {
                if(lineCount == 1) {
                    headerLines.add(line);
                }
                lineCount ++;
            }
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        }
        for (String s : headerLines){
            kell += s;
        }

        return kell;
    }*/


 /*   private StringBuilder getCoords(){
        File file = new File("coords.txt");
        StringBuilder text = new StringBuilder();

        try {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while ((line = br.readLine()) != null){
                text.append(line);
                text.append('\n');
            }
            br.close();
        } catch (IOException e) {e.printStackTrace();}
        textt = text;
        return text;
    }*/

    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        Constants.SCREEN_WIDTH = dm.widthPixels;
        Constants.SCREEN_HEIGHT = dm.heightPixels;

        sp = getSharedPreferences("Settings", Constants.CURRENT_CONTEXT.MODE_PRIVATE);


        setContentView(R.layout.activity_options);

        CheckBox bgCheckB = (CheckBox) findViewById(R.id.checkBox);
        CheckBox wpnCheckB = (CheckBox) findViewById(R.id.checkBox2);

        bgCheckB.setChecked(sp.getBoolean("BgMusic", true));
        wpnCheckB.setChecked(sp.getBoolean("WeaponSound", true));


        TextView save = (TextView) findViewById(R.id.textView);
        TextView close = (TextView) findViewById(R.id.textView2);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor editor = sp.edit();
                editor.putBoolean("BgMusic", bgCheckB.isChecked());
                editor.putBoolean("WeaponSound", wpnCheckB.isChecked());
                editor.commit();
                finish();

            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



 /*       TextView textView = (TextView) findViewById(R.id.szelesseg);
        textView.setText("Szelesseg: " + String.valueOf(Constants.SCREEN_WIDTH));*/


    }
}