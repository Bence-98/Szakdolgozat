package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HUD {

    private Rect key, restartButton;
    private Bitmap keyBmp, keyBmpFull, restartBitmap;

    public HUD() {
        key = new Rect(50, 25, 100, 75);

        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key_outline);

        keyBmp = Bitmap.createScaledBitmap(keyBmp, key.width(), key.height(), false);

        keyBmpFull = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key);
        keyBmpFull = Bitmap.createScaledBitmap(keyBmpFull, key.width(), key.height(), false);

        restartButton = new Rect(2200,15,2300,115);
        restartBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.restart);
        restartBitmap = Bitmap.createScaledBitmap(restartBitmap,restartButton.width(),restartButton.height(),false);
    }

    public Rect getRestartButton(){
        return restartButton;
    }

    public void draw(Canvas canvas, boolean keyPicked) {
        if (keyPicked)
            canvas.drawBitmap(keyBmpFull, key.left, key.top, null);
        else canvas.drawBitmap(keyBmp, key.left, key.top, null);

        canvas.drawBitmap(restartBitmap,restartButton.left,restartButton.top,null);
    }
}