package com.example.szakdolgozat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

public class SpriteSheet {
    private Bitmap bitmap;

    public SpriteSheet(Context context){
        BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;
        bitmap = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_red_run_100);

    }

    public Sprite getEnemySprite(){
        return new Sprite(this, new Rect(0,0,250,250));
    }

    public Bitmap getBitmap() {
        return bitmap;
    }
}
