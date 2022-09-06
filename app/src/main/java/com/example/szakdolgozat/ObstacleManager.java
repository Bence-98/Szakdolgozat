package com.example.szakdolgozat;

import android.graphics.Canvas;

import java.util.ArrayList;

public class ObstacleManager {
    private ArrayList<Obstacle> obstacles;
    private int color;


    public ObstacleManager(int[] level, int color) {
        this.color = color;
        obstacles = new ArrayList<>();

        for (int i = 0; level.length - 1 > i; i += 4) {
            obstacles.add(new Obstacle(level[i], level[i + 1], level[i + 2], level[i + 3], color));
        }


    }

    public boolean playerCollide(Player player) {
        for (Obstacle ob : obstacles) {
            if (ob.playerCollide(player))
                return true;
        }
        return false;
    }



    public void update() {

    }

    public void draw(Canvas canvas) {
        for (Obstacle ob : obstacles)
            ob.draw(canvas);
    }

}
