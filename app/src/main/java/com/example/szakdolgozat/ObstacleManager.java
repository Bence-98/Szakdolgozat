package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;


    public ObstacleManager(int[] level) {
        obstacles = new ArrayList<>();

        for (int i = 0; level.length - 1 > i; i += 2) {
            obstacles.add(new Obstacle(level[i], level[i + 1]));
        }


    }

    public ArrayList getArray(){
        return obstacles;
    }

    public boolean playerCollide(Player player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollide(player))
                return true;
        }
        return false;
    }

    public boolean projectileCollidePlatform(Projectile projectile) {
        for (Obstacle ob : obstacles)
            if (Rect.intersects(ob.getRectangle(), projectile.getRect()))
                return true;
        return false;
    }


    public void update() {
        for (Obstacle ob: obstacles) {
            ob.getRectangle().left--;
            ob.getRectangle().right--;
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
    }

}
