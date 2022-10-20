package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.media.MediaPlayer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {

    private ArrayList<Projectile> projectiles;
    private MediaPlayer shotSound;


    public ProjectileManager() {
        projectiles = new ArrayList<>();
        shotSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.shot);
        shotSound.setVolume(0.4f,0.4f);
    }

    public void update() {
        for (Projectile prtl : projectiles){
            prtl.update();
            if (prtl.getLifeTime() > 1000)
                deleteProjectile(prtl);
        }
    }


    public ArrayList getArray(){
        return projectiles;
    }

    public void draw(Canvas canvas) {
        for (Projectile prtl : projectiles)
            prtl.draw(canvas);
    }

    public void deleteProjectile(Projectile projectile){
        projectiles.remove(projectile);
    }

    public void fire(int x, int y, boolean direction, boolean whose) {
        projectiles.add(new Projectile(x, y, direction, whose));
        shotSound.start();
    }

}
