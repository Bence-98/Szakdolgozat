package com.example.szakdolgozat;

import android.graphics.Canvas;

import android.graphics.Rect;


public class Enemy implements GameObject {

    private Rect enemy;
    private int distance;
    private boolean direction = true;
    private int x = 0;
    private int speed = 5;
    private Animator animator;
    private int state;

    public Rect getEnemy() {
        return enemy;
    }

    public Enemy(int left, int top, int right, int bottom, int distance, Animator animator) {
        this.distance = distance;
        enemy = new Rect(left, top, right, bottom);
        this.animator = animator;
    }

    @Override
    public void draw(Canvas canvas) {
/*        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(enemy, paint);*/
        animator.draw(canvas, enemy, state);
    }

    public boolean getDirection(){
        return direction;
    }

    @Override
    public void update() {
        if (direction) {
            state = 0;
            x += speed;
            enemy.left += speed;
            enemy.right += speed;
        } else {
            state = 1;
            x -= speed;
            enemy.left -= speed;
            enemy.right -= speed;
        }
        if (x > distance || x < 0)
            direction = !direction;

    }

}
