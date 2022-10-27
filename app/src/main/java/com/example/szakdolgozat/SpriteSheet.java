package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;

import java.util.ArrayList;

public class SpriteSheet {
    private ArrayList<Bitmap> bitmap = new ArrayList<>();

    public SpriteSheet() {
  /*      BitmapFactory.Options bitmapOptions = new BitmapFactory.Options();
        bitmapOptions.inScaled = false;*/
        bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_red_run));
        bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_red_run_backwards));

        switch (Constants.CHARACTER_COLOR) {
            case 0:
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_idle));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_idle_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_run));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_run_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_jump));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_jump_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_death));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_black_death_backwards));
                break;
            case 1:
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_idle));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_idle_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_run));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_run_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_jump));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_jump_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_death));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_blue_death_backwards));
                break;
            case 2:
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_idle));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_idle_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_run));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_run_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_jump));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_jump_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_death));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_green_death_backwards));
                break;
            case 3:

                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_idle));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_idle_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_run));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_run_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_jump));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_jump_backwards));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_death));
                bitmap.add(BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.gunner_yellow_death_backwards));
                break;
        }
    }

    /*public void scale(){
        bitmap.set(2,Bitmap.createScaledBitmap(bitmap.get(2),600,100,false));
    }*/

    public Sprite[] getSpriteArray() {
        int width, height;
        width = bitmap.get(0).getWidth() / 6;
        height = bitmap.get(0).getHeight()-50;
        Sprite[] spriteArray = new Sprite[6];
        spriteArray[0] = new Sprite(this, new Rect(0, 0, width, height));
        spriteArray[1] = new Sprite(this, new Rect(width, 0, width * 2, height));
        spriteArray[2] = new Sprite(this, new Rect(width * 2, 0, width * 3, height));
        spriteArray[3] = new Sprite(this, new Rect(width * 3, 0, width * 4, height));
        spriteArray[4] = new Sprite(this, new Rect(width * 4, 0, width * 5, height));
        spriteArray[5] = new Sprite(this, new Rect(width * 5, 0, width * 6, height));
        return spriteArray;
    }

    public Bitmap getBitmap(int i) {
        return bitmap.get(i);
    }
}
