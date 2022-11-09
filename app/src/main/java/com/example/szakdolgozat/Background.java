package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Background {

    private int skyX = 0, rocksX = 0, cloudsX = 0;
    private Bitmap sky, rocks, clouds;


    public Background() {
        sky = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.sky);
        rocks = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.rocks);
        clouds = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.clouds);

        sky = Bitmap.createScaledBitmap(sky, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
        rocks = Bitmap.createScaledBitmap(rocks, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
        clouds = Bitmap.createScaledBitmap(clouds, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sky, skyX, 0, null);
        canvas.drawBitmap(sky, skyX + Constants.SCREEN_WIDTH, 0, null);
        canvas.drawBitmap(rocks, rocksX, 0, null);
        canvas.drawBitmap(rocks, rocksX + Constants.SCREEN_WIDTH, 0, null);
        canvas.drawBitmap(clouds, cloudsX, 0, null);
        canvas.drawBitmap(clouds, cloudsX + Constants.SCREEN_WIDTH, 0, null);
    }

    public void update(int scale) {
        if (scale % 20 == 0)
            skyX -= 1;
        if (scale % 10 == 0)
            rocksX -= 2;
        if (scale % 5 == 0)
            cloudsX -= 2;

        if (skyX < -Constants.SCREEN_WIDTH)
            skyX = 0;
        if (rocksX < -Constants.SCREEN_WIDTH)
            rocksX = 0;
        if (cloudsX < -Constants.SCREEN_WIDTH)
            cloudsX = 0;
    }

    public void reset() {
        skyX = 0;
        rocksX = 0;
        cloudsX = 0;

    }

}
