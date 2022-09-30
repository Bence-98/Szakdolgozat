package com.example.szakdolgozat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    private Bitmap bitmap;
    private int x = 200;

    public SpriteSheet(Context context) {
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_red_run_100);


    }

    public Sprite[] getEnemySpriteArray() {
        Sprite[] spriteArray = new Sprite[6];
        spriteArray[0] = new Sprite(this, new Rect(0, 0, 250, 250));
        spriteArray[1] = new Sprite(this, new Rect(300, 0, 550, 250));
        spriteArray[2] = new Sprite(this, new Rect(600, 0, 850, 250));
        spriteArray[3] = new Sprite(this, new Rect(900, 0, 1150, 250));
        spriteArray[4] = new Sprite(this, new Rect(1200, 0, 1450, 250));
        spriteArray[5] = new Sprite(this, new Rect(1500, 0, 1750, 250));
        return spriteArray;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
