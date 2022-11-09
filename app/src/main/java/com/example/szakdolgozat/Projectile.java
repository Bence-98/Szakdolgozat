package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;


public class Projectile implements GameObject {
    private Rect rect;
    boolean direction;
    private int lifeTime;
    private boolean whose; // true player, false enemy

    private Bitmap bullet;


    public Projectile(int x, int y, boolean direction, boolean whose) {
        rect = new Rect(16, 16, 16, 16);
        rect.set(x - 8, y - 8, x + 8, y + 8);
        this.direction = direction;
        this.whose = whose;

        bullet = BitmapFactory.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.bullet);
        bullet = Bitmap.createScaledBitmap(bullet, 16, 16, false);

        if (direction) {
            Matrix m = new Matrix();
            m.preScale(-1, 1);
            bullet = Bitmap.createBitmap(bullet, 0, 0, bullet.getWidth(), bullet.getHeight(), m, false);
        }
    }

    public Rect getRect() {
        return rect;
    }

    public boolean getWhose() {
        return whose;
    }

    @Override
    public void update() {
        if (direction) {
            rect.left += 25;
            rect.right += 25;
        } else {
            rect.left -= 25;
            rect.right -= 25;
        }
        lifeTime += 25;
    }

    public int getLifeTime() {
        return lifeTime;
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawBitmap(bullet, rect.left, rect.top, null);
    }
}
