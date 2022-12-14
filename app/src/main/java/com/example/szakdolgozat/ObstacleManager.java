package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class ObstacleManager {
    private final ArrayList<Obstacle> obstacles;

    public ObstacleManager(int[] level) {
        obstacles = new ArrayList<>();
        for (int i = 0; level.length - 1 > i; i += 3) {
            obstacles.add(new Obstacle(level[i], level[i + 1], level[i + 2]));
        }
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
        for (Obstacle ob : obstacles) {
            ob.update();
        }
    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
    }

}
