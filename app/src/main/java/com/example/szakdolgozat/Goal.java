package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Goal implements GameObject{

    private Rect rectangle;
    private int color;
    private Bitmap goal;

    public Rect getRectangle() {return rectangle;}


    public Goal(int[] coords, int color){
        this.color = color;
        rectangle = new Rect(coords[0],coords[1],coords[2],coords[3]);
        goal = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gate);
        goal = Bitmap.createScaledBitmap(goal,100,100,false);
    }

    public boolean playerCollideGoal(Player player){
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        /*Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);*/
        canvas.drawBitmap(goal,rectangle.left,rectangle.top,null);
    }

    @Override
    public void update() {
        rectangle.left--;
        rectangle.right--;

    }
}
