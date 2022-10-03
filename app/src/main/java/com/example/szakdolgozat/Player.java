package com.example.szakdolgozat;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;

public class Player implements GameObject {

    private Rect rectangle;
    private int color;

/*    private Animation idle;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation jumpRight;
    private Animation jumpLeft;
    private AnimationManager animManager;*/
    private int actualPositionX;
    private int state;


    private Animator animator;
    private SpriteSheet spriteSheet;


    public Rect getRectangle() {
        return rectangle;
    }

    public void setPlayerState(int state){
        this.state = state;
    }

    public Player(Rect rectangle, int color) {
        this.rectangle = rectangle;
        this.color = color;

        spriteSheet = new SpriteSheet();
        animator = new Animator(spriteSheet.getSpriteArray());

      /*  BitmapFactory bf = new BitmapFactory();
        Bitmap idleImg = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue);
        Bitmap walk1 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk1);
        Bitmap walk2 = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_walk2);
        Bitmap jump = bf.decodeResource(Constants.CURRENT_CONTEXT.getResources(), R.drawable.alienblue_jump);


        idle = new Animation(new Bitmap[]{idleImg}, 2);
        walkRight = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        Matrix m = new Matrix();
        m.preScale(-1, 1);
        walk1 = Bitmap.createBitmap(walk1, 0, 0, walk1.getWidth(), walk1.getHeight(), m, false);
        walk2 = Bitmap.createBitmap(walk2, 0, 0, walk2.getWidth(), walk2.getHeight(), m, false);

        walkLeft = new Animation(new Bitmap[]{walk1, walk2}, 0.5f);

        jumpRight = new Animation(new Bitmap[]{jump}, 1);


        Matrix n = new Matrix();
        n.preScale(-1, 1);
        jump = Bitmap.createBitmap(jump, 0, 0, jump.getWidth(), jump.getHeight(), n, false);

        jumpLeft = new Animation(new Bitmap[]{jump}, 1);

        animManager = new AnimationManager(new Animation[]{idle, walkRight, walkLeft, jumpRight, jumpLeft});*/
    }

    public void movingPlatforms(int x) {
        actualPositionX = x;
    }

    @Override
    public void draw(Canvas canvas) {
/*        Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        canvas.drawRect(rectangle, paint);*/
        //if (playerState == PlayerState.WALK_RIGHT)
        animator.draw(canvas, rectangle, state);
        //animManager.draw(canvas, rectangle);

    }

    public boolean getDirection() {
        if (state == 2 || state == 4 || state == 6)
            return true;
        else return false;
    }

    @Override
    public void update() {
        //animManager.update();
    }




    public void update(Point point) {
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

    }

    /*public void update(Point point, boolean right, boolean left) {
        float oldBottom = rectangle.bottom;

        //l,t,r,b
        rectangle.set(point.x - rectangle.width() / 2, point.y - rectangle.height() / 2, point.x + rectangle.width() / 2, point.y + rectangle.height() / 2);

        //int state = 0;
        if (rectangle.bottom - oldBottom == 0) {
            if (right)
                state = 1;
            else if (left)
                state = 2;
        } else {
            if (right)
                state = 3;
            else if (left)
                state = 4;
        }

        if (right || left) {
        animManager.playAnim(state);
        animManager.update();
        }

        if (!right && !left) {
            if (state == 3)
                state = 1;
            if (state == 4)
                state = 2;
            animManager.playAnim(state);
        }
    }*/

}
