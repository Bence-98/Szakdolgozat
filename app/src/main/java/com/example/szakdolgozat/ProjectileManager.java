package com.example.szakdolgozat;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.media.MediaPlayer;

import java.util.ArrayList;

public class ProjectileManager {

    private final ArrayList<Projectile> projectiles;
    private final MediaPlayer playerShotSound;
    private final MediaPlayer enemyShotSound;
    boolean soundOn;

    public ProjectileManager() {
        projectiles = new ArrayList<>();
        playerShotSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.shot);
        playerShotSound.setVolume(0.4f, 0.4f);

        enemyShotSound = MediaPlayer.create(Constants.CURRENT_CONTEXT, R.raw.enemyshot);
        enemyShotSound.setVolume(0.4f, 0.4f);

        SharedPreferences sp = Constants.CURRENT_CONTEXT.getSharedPreferences("Settings", Context.MODE_PRIVATE);
        soundOn = sp.getBoolean("WeaponSound", true);
    }

    public void update() {
        for (Projectile prtl : projectiles) {
            prtl.update();
            if (prtl.getLifeTime() > 1000)
                deleteProjectile(prtl);
        }
    }

    public ArrayList<Projectile> getArray() {
        return projectiles;
    }

    public void draw(Canvas canvas) {
        for (Projectile prtl : projectiles)
            prtl.draw(canvas);
    }

    public void deleteProjectile(Projectile projectile) {
        projectiles.remove(projectile);
    }

    public void floatLeft() {
        for (Projectile prtl : projectiles) {
            prtl.getRect().left--;
            prtl.getRect().right--;
        }
    }

    public void fire(int x, int y, boolean direction, boolean whose) {
        projectiles.add(new Projectile(x, y, direction, whose));
        if (soundOn)
            if (whose)
                playerShotSound.start();
            else
                enemyShotSound.start();
    }
}