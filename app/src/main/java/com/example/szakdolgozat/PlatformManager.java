package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Point;

import java.util.ArrayList;

public class PlatformManager {
    private ArrayList<Platform> platforms;
    private AnimationManager animationManager;

    public PlatformManager(int[] level, int color) {
        platforms = new ArrayList<>();

        for (int i = 0; level.length - 1 > i; i += 4) {
            platforms.add(new Platform(level[i], level[i + 1], level[i + 2], level[i + 3], color));
        }
    }

/*    public boolean playerCollidePlatform(Player player) {
        for (Platform pf : platforms)
            if (pf.playerCollidePlatform(player))
                return true;
        return false;
    }*/

    public boolean canIGoRight(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatform().contains(point.x + 55, point.y + 50) || pf.getPlatform().contains(point.x + 55, point.y - 50) || pf.getPlatform().contains(point.x + 55, point.y))
                return false;
        return true;
    }

    public boolean canIGoLeft(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatform().contains(point.x - 55, point.y + 50) || pf.getPlatform().contains(point.x - 55, point.y - 50) || pf.getPlatform().contains(point.x - 55, point.y))
                return false;
        return true;
    }


    public int canIGoDown(Point point){
        for (Platform pf : platforms)
            if (pf.getPlatform().contains(point.x + 50, point.y + 60) || pf.getPlatform().contains(point.x - 50, point.y + 60))
                return pf.getPlatform().top-(point.y+51);
            return 10;
    }

    public boolean canIGoUp(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatform().contains(point.x + 50, point.y - 55) || pf.getPlatform().contains(point.x - 50, point.y - 55))
                return false;
        return true;

    }

    public void draw(Canvas canvas) {
        for (Platform pf : platforms)
            pf.draw(canvas);
            //animationManager.draw(canvas, pf.getPlatform());
    }
}
