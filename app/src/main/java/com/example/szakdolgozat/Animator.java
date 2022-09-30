package com.example.szakdolgozat;

import android.graphics.Canvas;

public class Animator {
    private Sprite[] enemySpriteArray;
    private int movingFrameIndex = 1;
    private int frameTime = 10;


    public Animator(Sprite[] enemySpriteArray) {
        this.enemySpriteArray = enemySpriteArray;
    }

    public void draw(Canvas canvas, Enemy enemy) {
        if (enemy.getDirection()) {

            drawFrame(canvas, enemy, enemySpriteArray[movingFrameIndex]);


            frameTime--;
            if (frameTime == 0) {
                frameTime = 10;
                movingFrameIndex++;
                if (movingFrameIndex == 6)
                    movingFrameIndex = 0;

            }
        }
    }

    public void drawFrame(Canvas canvas, Enemy enemy, Sprite sprite) {
        sprite.draw(canvas, enemy.getEnemy());
    }
}
