package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {

    private Rect rectangle;

    private int state;

    private Animator animator;
    private SpriteSheet spriteSheet;
    private boolean alive;


    public Rect getRectangle() {
        return rectangle;
    }

    public void setPlayerState(int state) {
        this.state = state;
    }

    public Player(Rect rectangle) {
        this.rectangle = rectangle;

        spriteSheet = new SpriteSheet();
        animator = new Animator(spriteSheet.getSpriteArray());
    }


    @Override
    public void draw(Canvas canvas) {
        animator.draw(canvas, rectangle, state);
    }

    public boolean getDirection() {
        if (state == 2 || state == 4 || state == 6)
            return true;
        else return false;
    }

    @Override
    public void update() {
    }

    public boolean playerAlive() {
        return alive;
    }

    public void playerDie(boolean alive) {
        this.alive = alive;
    }


    public void update(Point point) {
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }
}
