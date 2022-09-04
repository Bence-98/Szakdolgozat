package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Platform implements  GameObject{

    private Rect platform;
    private int color;

    public Rect getPlatform(){
        return platform;
    }

    public Platform(int pleft, int ptop, int pright, int pbottom, int color) {
        this.color = color;
        platform = new Rect(pleft,ptop,pright,pbottom);
    }

    public boolean playerCollidePlatform(Player player){
        return Rect.intersects(platform, player.getRectangle());
    }

    @Override
    public void draw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(color);
        canvas.drawRect(platform, paint);
    }

    @Override
    public void update() {

    }

}
