package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class Goal {

    private Rect rectangle;
    private Bitmap goalClosed, goalOpen;

    public Rect getRectangle() {
        return rectangle;
    }


    public Goal(int[] coords) {
        rectangle = new Rect(coords[0], coords[1], coords[0] + 100, coords[1] + 200);
        goalClosed = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gate_closed);
        goalClosed = Bitmap.createScaledBitmap(goalClosed, 100, 200, false);
        goalOpen = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gate_open);
        goalOpen = Bitmap.createScaledBitmap(goalOpen, 100, 200, false);


    }

    public boolean playerCollideGoal(Player player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }


    public void draw(Canvas canvas, boolean keyPicked) {
        /*Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(rectangle,paint);*/
        if (keyPicked)
            canvas.drawBitmap(goalOpen, rectangle.left, rectangle.top, null);
        else
            canvas.drawBitmap(goalClosed, rectangle.left, rectangle.top, null);
    }


    public void update() {
        rectangle.left--;
        rectangle.right--;

    }
}
