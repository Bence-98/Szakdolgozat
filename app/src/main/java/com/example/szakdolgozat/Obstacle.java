package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Rect rectangle;
    private int color;

    public Rect getRectangle() {
        return rectangle;
    }


    public Obstacle(int obsLeft, int obsTop, int obsRight, int obsBottom, int color) {
        this.color = color;
        rectangle = new Rect(obsLeft,obsTop, obsRight, obsBottom);
    }

    public boolean playerCollide(Player player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}
