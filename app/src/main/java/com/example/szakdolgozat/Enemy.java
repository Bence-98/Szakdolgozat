package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;


public class Enemy implements GameObject {

    private final Rect enemy;
    private final int distance;
    private boolean direction = true;
    private int x = 0;
    private final Animator animator;
    private int state;
    private int count;
    private final ProjectileManager projectileManager;
    private final int left;

    public Rect getEnemy() {
        return enemy;
    }

    public Enemy(int left, int top, int distance, Animator animator, ProjectileManager projectileManager) {
        this.left = left;
        this.distance = distance;
        enemy = new Rect(left, top, left + 100, top + 100);
        this.animator = animator;
        this.projectileManager = projectileManager;
    }

    @Override
    public void draw(Canvas canvas) {
        animator.draw(canvas, enemy, state);
    }


    @Override
    public void update() {
    }

    public void update(int actPosX) {
        count++;
        if (count > 60) {
            count = 0;
            if (actPosX > left - Constants.SCREEN_WIDTH) {
                if (direction)
                    projectileManager.fire(enemy.left + 100, enemy.top + 50, true, false);
                else projectileManager.fire(enemy.left, enemy.top + 50, false, false);
            }
        }
        int speed = 5;
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
