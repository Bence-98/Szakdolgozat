package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HUD {

    private final Rect key;
    private final Rect restartButton;
    private final Rect upArrow;
    private final Rect leftArrow;
    private final Rect rightArrow;
    private final Rect fireButton;
    private Bitmap keyBmp, keyBmpFull, restartBitmap, upBitmap, leftBitmap, rightBitmap, fireBitmap;

    public HUD() {
        key = new Rect(50, 25, 100, 75);
        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key_outline);
        keyBmp = Bitmap.createScaledBitmap(keyBmp, key.width(), key.height(), false);
        keyBmpFull = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key);
        keyBmpFull = Bitmap.createScaledBitmap(keyBmpFull, key.width(), key.height(), false);

        restartButton = new Rect(2200, 15, 2300, 115);
        restartBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.restart);
        restartBitmap = Bitmap.createScaledBitmap(restartBitmap, restartButton.width(), restartButton.height(), false);

        upArrow = new Rect(2000, 850, 2150, 1000);
        leftArrow = new Rect(50, 850, 200, 1000);
        rightArrow = new Rect(250, 850, 400, 1000);
        fireButton = new Rect(2000, 650, 2150, 800);
        upBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.upbutton);
        upBitmap = Bitmap.createScaledBitmap(upBitmap, upArrow.width(), upArrow.height(), false);
        leftBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.leftbutton);
        leftBitmap = Bitmap.createScaledBitmap(leftBitmap, upArrow.width(), upArrow.height(), false);
        rightBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rightbutton);
        rightBitmap = Bitmap.createScaledBitmap(rightBitmap, upArrow.width(), upArrow.height(), false);
        fireBitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.firebutton);
        fireBitmap = Bitmap.createScaledBitmap(fireBitmap, upArrow.width(), upArrow.height(), false);
    }

    public Rect getRestartButton() {
        return restartButton;
    }

    public Rect getupArrow() {
        return upArrow;
    }

    public Rect getleftArrow() {
        return leftArrow;
    }

    public Rect getrightArrow() {
        return rightArrow;
    }

    public Rect getfireButton() {
        return fireButton;
    }

    public void draw(Canvas canvas, boolean keyPicked) {
        if (keyPicked)
            canvas.drawBitmap(keyBmpFull, key.left, key.top, null);
        else canvas.drawBitmap(keyBmp, key.left, key.top, null);

        canvas.drawBitmap(restartBitmap, restartButton.left, restartButton.top, null);
        canvas.drawBitmap(upBitmap, upArrow.left, upArrow.top, null);
        canvas.drawBitmap(leftBitmap, leftArrow.left, leftArrow.top, null);
        canvas.drawBitmap(rightBitmap, rightArrow.left, rightArrow.top, null);
        canvas.drawBitmap(fireBitmap, fireButton.left, fireButton.top, null);
    }
}