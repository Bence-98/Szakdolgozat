package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Goal implements GameObject{

    private Rect rectangle;
    private int color;

    public Rect getRectangle() {return rectangle;}


    public Goal(int[] coords, int color){
        this.color = color;
        rectangle = new Rect(coords[0],coords[1],coords[2],coords[3]);
    }

    public boolean playerCollideGoal(Player player){
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);
    }

    @Override
    public void update() {
        rectangle.left--;
        rectangle.right--;

    }
}
