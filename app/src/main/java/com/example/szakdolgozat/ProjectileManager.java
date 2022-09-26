package com.example.szakdolgozat;

import android.graphics.Canvas;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ProjectileManager {

    private ArrayList<Projectile> projectiles;


    public ProjectileManager() {
        projectiles = new ArrayList<>();
    }

    public void update() {
        for (Projectile prtl : projectiles)
            prtl.update();
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

    public void fire(int x, int y, boolean direction) {
        projectiles.add(new Projectile(x, y, direction));
    }

}
