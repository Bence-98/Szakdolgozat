package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class Platform implements GameObject {
    private final ArrayList<Rect> platform;
    private final Rect hitbox;
    private final int top;
    private Bitmap grass, dirt;

    public Rect getPlatformHitbox() {
        return hitbox;
    }

    public Platform(int firstLeft, int top, int lastRight, int floating) {
        platform = new ArrayList<>();
        this.top = top;
        grass = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.grass);
        dirt = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.dirt);
        grass = Bitmap.createScaledBitmap(grass, 100, 100, false);
        dirt = Bitmap.createScaledBitmap(dirt, 100, 100, false);

        if (floating == 1)
            hitbox = new Rect(firstLeft, top, lastRight, top + 100);
        else
            hitbox = new Rect(firstLeft, top, lastRight, Constants.SCREEN_HEIGHT);

        int shift = 0;
        while (firstLeft + shift <= lastRight - 100) {
            platform.add(new Rect(firstLeft + shift, top, firstLeft + shift + 100, top + 100));
            if (floating == 0) {
                int shiftDown = 0;
                while (top + shiftDown <= Constants.SCREEN_HEIGHT) {
                    platform.add(new Rect(firstLeft + shift, top + shiftDown, firstLeft + shift + 100, top + shiftDown + 100));
                    shiftDown += 100;
                }
            }
            shift += 100;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        for (Rect pf : platform) {
            if (pf.left < Constants.SCREEN_WIDTH && pf.right > 0) {
                if (pf.top == top)
                    canvas.drawBitmap(grass, pf.left, pf.top, null);
                else canvas.drawBitmap(dirt, pf.left, pf.top, null);
            }
        }
    }

    @Override
    public void update() {
        if (hitbox.right > 0) {
            for (Rect pf : platform) {
                pf.left--;
                pf.right--;
            }
            hitbox.left--;
            hitbox.right--;
        }
    }
}