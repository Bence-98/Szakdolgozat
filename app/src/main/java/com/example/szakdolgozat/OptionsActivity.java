package com.example.szakdolgozat;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Environment;
import android.util.DisplayMetrics;
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

        TextView textView3 = (TextView) findViewById(R.id.ido);
        textView3.setText("Ido: " + String.valueOf(System.currentTimeMillis()));


/*        TextView textView4 = (TextView) findViewById(R.id.olvasas);
        textView4.setText("Asd" + getCoords());*/

    }
}