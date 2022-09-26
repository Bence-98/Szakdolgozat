package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;


public class Projectile implements GameObject {
    private Rect rect;
    boolean direction;

    private Bitmap bullet;


    public Projectile(int x, int y, boolean direction) {
        rect = new Rect(16, 16, 16, 16);
        rect.set(x - 8, y - 8, x + 8, y + 8);
        this.direction = direction;

        bullet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bullet);
        bullet = Bitmap.createScaledBitmap(bullet, 16, 16, false);

        if (direction == true) {
            Matrix m = new Matrix();
            m.preScale(-1, 1);
            bullet = Bitmap.createBitmap(bullet, 0, 0, bullet.getWidth(), bullet.getHeight(), m, false);
        }
    }

    public Rect getRect(){
        return rect;
    }


    @Override
    public void update() {
        if (direction){
            rect.left += 20;
            rect.right += 20;
        }else{
            rect.left -= 20;
            rect.right -= 20;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bullet, rect.left, rect.top, null);
    }
}
