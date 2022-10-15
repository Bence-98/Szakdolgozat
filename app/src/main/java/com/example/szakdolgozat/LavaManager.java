package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class LavaManager {
    private ArrayList<Lava> lavas;
    private int count;

    public LavaManager(int[] level){
        lavas = new ArrayList<>();

        for (int i = 0; level.length-1 > i; i += 3){
            lavas.add(new Lava(level[i], level[i + 1], level[i + 2]));
        }
    }


    public void changeWave(){
        count++;
        if (count == 10) {
            for (Lava lv: lavas)
                lv.changeWave();
            count = 0;
        }
    }

    public boolean playerCollideLava(Player player){
        for (Lava lv: lavas)
            if (Rect.intersects(lv.getLavaHitbox(),player.getRectangle()))
                return true;
        return false;
    }


    public void draw(Canvas canvas){
        for (Lava lv: lavas)
            lv.draw(canvas);

    }

    public void update(){
        for (Lava lv: lavas)
            lv.update();
    }

}
