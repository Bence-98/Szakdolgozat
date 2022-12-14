package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;

public class Animator {
    private final Sprite[] spriteArray;
    private int movingFrameIndex = 0;
    private int frameTime = 10;

    public Animator(Sprite[] spriteArray) {
        this.spriteArray = spriteArray;
    }

    public void draw(Canvas canvas, Rect rect, int state) {
        drawFrame(canvas, rect, spriteArray[movingFrameIndex], state);
        if (!(state == PlayerState.JUMP_RIGHT.ordinal() || state == PlayerState.JUMP_LEFT.ordinal())) {
            frameTime--;
            if (frameTime == 0) {
                frameTime = 10;
                movingFrameIndex++;
                if (movingFrameIndex == 6)
                    movingFrameIndex = 0;
            }
        } else movingFrameIndex = 1;
    }

    public void drawFrame(Canvas canvas, Rect rect, Sprite sprite, int state) {
        sprite.draw(canvas, rect, state);
    }
}