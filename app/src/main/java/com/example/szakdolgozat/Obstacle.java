package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;

public class Obstacle implements GameObject {

    private Rect rectangle;
    private Bitmap spike;

    public Rect getRectangle() {
        return rectangle;
    }


    public Obstacle(int left, int top,int rotate) {
        rectangle = new Rect(left,top,left+100,top+50);
        spike = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.spikes);
        spike = Bitmap.createScaledBitmap(spike,100,50,false);

        Matrix matrix = new Matrix();
        matrix.postRotate(rotate);
        spike = Bitmap.createBitmap(spike,0,0,spike.getWidth(), spike.getHeight(), matrix, true);


    }

    public boolean playerCollide(Player player) {
        return Rect.intersects(rectangle, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(spike,rectangle.left,rectangle.top,null);
        //canvas.drawRect(rectangle, paint);
    }

    @Override
    public void update() {

    }
}
