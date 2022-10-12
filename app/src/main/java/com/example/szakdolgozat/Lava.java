package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;

import java.util.ArrayList;

public class Lava {

    private ArrayList<Rect> lava;
    private Rect hitbox;
    private boolean direction = true;

    private Bitmap lavaBmp, lavaBmpRev;

    public Lava(int firstLeft, int top, int lastLeft) {
        lava = new ArrayList<>();

        lavaBmp = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.lava);
        lavaBmp = Bitmap.createScaledBitmap(lavaBmp, 100, 100, false);

        Matrix m = new Matrix();
        m.preScale(-1, 1);

        lavaBmpRev = Bitmap.createBitmap(lavaBmp, 0, 0, 100, 100, m, false);

        hitbox = new Rect(firstLeft, top, lastLeft + 100, top + 100);

        int shift = 0;
        while (firstLeft + shift <= lastLeft) {
            lava.add(new Rect(firstLeft + shift, top, firstLeft + shift + 100, top + 100));
            shift += 100;
        }
    }


    public Rect getLavaHitbox(){
        return hitbox;
    }


    public void changeWave(){
        direction = !direction;
    }

    public void draw(Canvas canvas) {
        for (Rect lv : lava) {
            if (direction)
                canvas.drawBitmap(lavaBmp, lv.left, lv.top, null);
            else
                canvas.drawBitmap(lavaBmpRev, lv.left, lv.top, null);

        }
    }


    public void update() {
        for (Rect lv: lava){
            lv.left--;
            lv.right--;
        }
        hitbox.left--;
        hitbox.right--;
    }
}
