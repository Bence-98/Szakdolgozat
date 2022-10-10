package com.example.szakdolgozat;


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;

public class Platform implements GameObject {

    private Rect platform;
    private int color;
    private Bitmap bricks;
    //private BitmapDrawable bitmapDrawable;


    public Rect getPlatform() {
        return platform;
    }


    public Platform(int pLeft, int pTop, int pRight, int pBottom, int color) {
        this.color = color;
        platform = new Rect(pLeft, pTop, pRight, pBottom);
        bricks = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.brick);

        bricks = Bitmap.createBitmap(bricks);

        //bricks = Bitmap.createScaledBitmap(bricks,platform.right-platform.left,platform.bottom-platform.top,false);

    }

    public boolean playerCollidePlatform(Player player) {
        return Rect.intersects(platform, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(platform, paint);
        //animationManager.draw(canvas,platform);
        //canvas.drawBitmap(bricks,platform.left,platform.top,null);
        canvas.drawRect(0,100,21,21, paint);
    }



    @Override
    public void update() {

    }

}
