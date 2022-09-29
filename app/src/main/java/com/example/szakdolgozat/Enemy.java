package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;


public class Enemy implements GameObject {

    private Rect enemy;
    private int distance;
    private boolean direction = true;
    private int x = 0;
    private int speed = 5;
    private Sprite sprite;

    public Rect getEnemy() {
        return enemy;
    }

    public Enemy(int left, int top, int right, int bottom, int distance, Sprite sprite) {
        this.distance = distance;
        enemy = new Rect(left, top, right, bottom);
        this.sprite = sprite;
    }

    @Override
    public void draw(Canvas canvas) {
/*        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(enemy, paint);*/

        sprite.draw(canvas,enemy);
    }


    @Override
    public void update() {
        if (direction) {
            x += speed;
            enemy.left += speed;
            enemy.right += speed;
        } else {
            x -= speed;
            enemy.left -= speed;
            enemy.right -= speed;
        }
        if (x > distance || x < 0)
            direction = !direction;

    }

}
