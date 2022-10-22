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
    private int count;
    private ProjectileManager projectileManager;
    private int left;

    public Rect getEnemy() {
        return enemy;
    }

    public Enemy(int left, int top, int right, int bottom, int distance, Animator animator, ProjectileManager projectileManager) {
        this.left = left;
        this.distance = distance;
        enemy = new Rect(left, top, right, bottom);
        this.animator = animator;
        this.projectileManager = projectileManager;
    }

    @Override
    public void draw(Canvas canvas) {
/*        Paint paint = new Paint();
        paint.setColor(Color.MAGENTA);
        canvas.drawRect(enemy, paint);*/
        animator.draw(canvas, enemy, state);
    }

    public boolean getDirection() {
        return direction;
    }

    @Override
    public void update() {

    }

    public void update(int actPosX) {
        count++;
        if (count > 120) {
            count = 0;
            if (actPosX > left-Constants.SCREEN_WIDTH) {
                if (direction)
                    projectileManager.fire(enemy.left + 100, enemy.top + 50, true, false);
                else projectileManager.fire(enemy.left, enemy.top + 50, false, false);
            }
        }
        if (direction) {
            state = PlayerState.ENEMY_WALK_RIGHT.ordinal();
            x += speed;
            enemy.left += speed;
            enemy.right += speed;
        } else {
            state = PlayerState.ENEMY_WALK_LEFT.ordinal();
            x -= speed;
            enemy.left -= speed;
            enemy.right -= speed;
        }
        if (x > distance || x < 0)
            direction = !direction;

    }

}
