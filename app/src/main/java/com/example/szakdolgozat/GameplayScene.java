package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;


public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private int isGoing;
    private Rect upArrow, leftArrow, rightArrow;
    private boolean isGoingLeft, isGoingRight;
    private PlatformManager platformManager;
    private LevelCoords levelCoords;
    private int[] currLvl;
    private int[] currLvlObs;


    private boolean gameOver = false;
    private long gameOverTime;

    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200), Color.rgb(0, 0, 0));
        playerPoint = new Point(1000, 940);
        player.update(playerPoint);

        levelCoords = new LevelCoords();
        currLvl = levelCoords.getLevelOne();
        platformManager = new PlatformManager(currLvl, Color.BLACK);


        currLvl = levelCoords.getLevelOneObs();
        obstacleManager = new ObstacleManager(currLvl, Color.RED);
    }

    public void reset() {
        playerPoint = new Point(1000,940);
        player.update(playerPoint);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }


    public void jumping() {
        for (int i = 0; i < 25; i++) {
            try {
                wait(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (platformManager.canIGoUp(playerPoint))
                playerPoint.y -= 10;
        }
    }

    public void goingLeft() {
        int steps = 0;
        while (isGoingLeft && playerPoint.x > 50 && platformManager.canIGoLeft(playerPoint) && steps < 20) {
            playerPoint.x--;
            steps++;
        }
    }

    public void goingRight() {
        int steps = 0;
        while (isGoingRight && playerPoint.x + 50 < Constants.SCREEN_WIDTH && platformManager.canIGoRight(playerPoint) && steps < 20) {
            playerPoint.x++;
            steps++;
        }
    }

    public void gravity() {
        if (playerPoint.y < Constants.SCREEN_HEIGHT - 50 && platformManager.canIGoDown(playerPoint))
            playerPoint.y += 10;
    }



    @Override
    public void receiveTouch(MotionEvent event) {

        int pointerIndex = event.getActionIndex();
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (upArrow.contains((int) event.getX(pointerIndex), (int) event.getY(pointerIndex)))
                    jumping();
                if (leftArrow.contains((int) event.getX(pointerIndex), (int) event.getY(pointerIndex)))
                    isGoingLeft = true;
                if (rightArrow.contains((int) event.getX(pointerIndex), (int) event.getY(pointerIndex)))
                    isGoingRight = true;
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                isGoing = 0;
                isGoingRight = false;
                isGoingLeft = false;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        player.draw(canvas);
        obstacleManager.draw(canvas);
        platformManager.draw(canvas);

        Paint arrowPaint = new Paint();
        arrowPaint.setColor(Color.rgb(125,125,125));
        arrowPaint.setAlpha(100);
        upArrow = new Rect(2000, 850, 2150, 1000);
        leftArrow = new Rect(50, 850, 200, 1000);
        rightArrow = new Rect(250, 850, 400, 1000);
        canvas.drawRect(upArrow, arrowPaint);
        canvas.drawRect(leftArrow, arrowPaint);
        canvas.drawRect(rightArrow, arrowPaint);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    @Override
    public void update() {
        goingLeft();
        goingRight();
        gravity();
        if (!gameOver) {
            player.update(playerPoint);
            obstacleManager.update();
            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
        if (gameOver && System.currentTimeMillis() - gameOverTime > 2000) {
            reset();
            gameOver = false;
        }
    }

    private void drawCenterText(Canvas canvas, Paint paint, String text) {
        paint.setTextAlign(Paint.Align.LEFT);
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }
}
