package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform implements GameObject {

    private Rect platform;
    private int color;
    private Animation justGrass;
    private AnimationManager animationManager;

    public Rect getPlatform() {
        return platform;
    }


    public Platform(int pLeft, int pTop, int pRight, int pBottom, int color) {
        this.color = color;
        platform = new Rect(pLeft, pTop, pRight, pBottom);

        BitmapFactory bf = new BitmapFactory();
        Bitmap grass = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.grass);

        justGrass = new Animation(new Bitmap[]{grass},2);

        animationManager = new AnimationManager(new Animation[]{justGrass});
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
    }



    @Override
    public void update() {
        //animationManager.update();
    }

}
