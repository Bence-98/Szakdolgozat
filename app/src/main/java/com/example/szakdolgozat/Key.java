package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Key {

    private final Rect key;
    private Bitmap keyBmp;
    private boolean visible = false;

    public Key() {
        key = new Rect(0, 1100, 50, 1150);
        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.key);
        keyBmp = Bitmap.createScaledBitmap(keyBmp, 50, 50, false);
    }

    public void setCoords(int left, int top) {
        key.left = left;
        key.top = top;
        key.right = left + 50;
        key.bottom = top + 50;
    }

    public boolean playerCollideKey(Player player) {
        if (Rect.intersects(player.getRectangle(), key)) {
            visible = false;
            return true;
        }
        return false;
    }

    public void setVisible() {
        visible = true;
    }

    public void draw(Canvas canvas) {
        if (visible)
            canvas.drawBitmap(keyBmp, key.left, key.top, null);
    }

    public void update() {
        key.left--;
        key.right--;
    }
}