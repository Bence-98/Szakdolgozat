package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {
    private final Rect rectangle;
    private int state;
    private final Animator animator;
    private boolean alive = true;

    public Rect getRectangle() {
        return rectangle;
    }

    public void setPlayerState(int state) {
        this.state = state;
    }

    public Player(Rect rectangle) {
        this.rectangle = rectangle;

        SpriteSheet spriteSheet = new SpriteSheet();
        animator = new Animator(spriteSheet.getSpriteArray());
    }

    public boolean getAlive() {
        return alive;
    }

    @Override
    public void draw(Canvas canvas) {
        animator.draw(canvas, rectangle, state);
    }

    public boolean getDirection() {
        return state == 2 || state == 4 || state == 6;
    }

    @Override
    public void update() {
    }

    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public void update(Point point) {
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);
    }
}
