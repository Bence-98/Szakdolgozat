package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;

public class HUD {

    private Rect key;
    private Bitmap keyBmp, keyBmp_full;
    private boolean keyPickedUp;
    public HUD (){
        keyPickedUp = false;
        key = new Rect(50,25,100,75);
        keyBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(),R.drawable.hud_key_outline);
        keyBmp = Bitmap.createScaledBitmap(keyBmp,key.width(),key.height(),false);

        keyBmp_full = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.hud_key);
        keyBmp_full = Bitmap.createScaledBitmap(keyBmp_full,key.width(),key.height(),false);


    }

    public void pickUpKey(){
        keyPickedUp = true;

    }

    public void draw(Canvas canvas){
        if (keyPickedUp)
            canvas.drawBitmap(keyBmp_full,key.left,key.top,null);
        else
        canvas.drawBitmap(keyBmp,key.left,key.top,null);
    }

}