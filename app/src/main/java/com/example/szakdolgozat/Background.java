package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;


public class Background {

    int landscapeX = 0, buildingsX = 0;
    Bitmap landscape, buildings;


    public Background() {
        landscape = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.landscape);
        buildings = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.buildings);

        landscape = Bitmap.createScaledBitmap(landscape, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);
        buildings = Bitmap.createScaledBitmap(buildings, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, false);

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(landscape, landscapeX, 0, null);
        canvas.drawBitmap(landscape, landscapeX + Constants.SCREEN_WIDTH, 0, null);
        canvas.drawBitmap(buildings, buildingsX, 0, null);
        canvas.drawBitmap(buildings, buildingsX + Constants.SCREEN_WIDTH, 0, null);

    }

    public void update(int scale) {
        if (scale % 10 == 0)
        landscapeX -= 1;
        if (scale % 5 == 0)
        buildingsX -= 2;
        if (landscapeX < -Constants.SCREEN_WIDTH)
            landscapeX = 0;
        if (buildingsX < -Constants.SCREEN_WIDTH)
            buildingsX = 0;
    }


}
