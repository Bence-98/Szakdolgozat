package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

import java.util.ArrayList;

public class PlatformManager {
    private final ArrayList<Platform> platforms;

    public PlatformManager(int[] level) {
        platforms = new ArrayList<>();
        for (int i = 0; level.length - 1 > i; i += 4) {
            platforms.add(new Platform(level[i], level[i + 1], level[i + 2], level[i + 3]));
        }
    }

    public boolean projectileCollidePlatform(Projectile projectile) {
        for (Platform pf : platforms)
            if (Rect.intersects(pf.getPlatformHitbox(), projectile.getRect()))
                return true;
        return false;
    }

    public boolean canIGoRight(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatformHitbox().contains(point.x + 45, point.y + 50) || pf.getPlatformHitbox().contains(point.x + 45, point.y - 45) || pf.getPlatformHitbox().contains(point.x + 45, point.y))
                return false;
        return true;
    }

    public boolean canIGoLeft(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatformHitbox().contains(point.x - 45, point.y + 50) || pf.getPlatformHitbox().contains(point.x - 45, point.y - 45) || pf.getPlatformHitbox().contains(point.x - 45, point.y))
                return false;
        return true;
    }


    public int canIGoDown(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatformHitbox().contains(point.x + 40, point.y + 70) || pf.getPlatformHitbox().contains(point.x - 40, point.y + 70))
                return pf.getPlatformHitbox().top - (point.y + 51);
        return 20;
    }

    public boolean canIGoUp(Point point) {
        for (Platform pf : platforms)
            if (pf.getPlatformHitbox().contains(point.x + 40, point.y - 55) || pf.getPlatformHitbox().contains(point.x - 40, point.y - 55))
                return false;
        return true;

    }

    public void draw(Canvas canvas) {
        for (Platform pf : platforms)
            pf.draw(canvas);
    }

    public void update() {
        for (Platform pf : platforms)
            pf.update();
    }
}
