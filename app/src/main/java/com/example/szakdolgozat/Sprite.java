package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Sprite {
    private SpriteSheet spriteSheet;
    private Rect rect;

    public Sprite(SpriteSheet spriteSheet, Rect rect){
        this.spriteSheet = spriteSheet;
        this.rect = rect;
    }

    public void draw(Canvas canvas, Rect destRect){
        canvas.drawBitmap(spriteSheet.getBitmap(), rect, new Rect(destRect.left,destRect.top,destRect.right,destRect.bottom), null);

    }
}
