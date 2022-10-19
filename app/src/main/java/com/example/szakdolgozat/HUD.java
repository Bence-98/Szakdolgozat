package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HUD {

    private Rect key;
    private Bitmap keyBmp;
    public HUD() {
        key = new Rect(50, 25, 100, 75);

        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key_outline);

        keyBmp = Bitmap.createScaledBitmap(keyBmp, key.width(), key.height(), false);
    }

    public void pickUpKey() {
        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key);
        keyBmp = Bitmap.createScaledBitmap(keyBmp, key.width(), key.height(), false);
    }


    public void draw(Canvas canvas) {
        canvas.drawBitmap(keyBmp, key.left, key.top, null);
    }
}