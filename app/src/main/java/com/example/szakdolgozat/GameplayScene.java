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
    private Rect upArrow, leftArrow, rightArrow;
    private boolean isGoingLeft, isGoingRight;
    private PlatformManager platformManager;
    private LevelCoords levelCoords;
    private int currLvlStartingLine = -3;
    private int[] currLvlCoords;
    private Goal goal;
    private int movingId = 0;
    private int actualPositionX;
    private Background background;

    private boolean goalReached = false;
    private boolean gameOver = false;
    private long gameOverTime;

    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200), Color.rgb(0, 0, 0));
        playerPoint = new Point(250, 940);

        levelCoords = new LevelCoords();
        background = new Background();
        nextLevel();

    }


    public void nextLevel() {
        playerPoint.set(250, 940);
        player.update(playerPoint, true, isGoingLeft);
        currLvlStartingLine += 3;
        background.reset();
        currLvlCoords = levelCoords.getCoords(currLvlStartingLine);
        platformManager = new PlatformManager(currLvlCoords, Color.BLACK);


        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 1);
        obstacleManager = new ObstacleManager(currLvlCoords, Color.RED);

        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 2);
        goal = new Goal(currLvlCoords, Color.GREEN);

    }


    public void reset() {
        currLvlStartingLine -=3;
        nextLevel();
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }


    public void jumping() {
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(5);
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
        if (!gameOver && !goalReached)
        while (isGoingRight && platformManager.canIGoRight(playerPoint) && steps < 20) {
            if (playerPoint.x > 750) {
                actualPositionX++;
                background.update(actualPositionX);
                platformManager.update();
                obstacleManager.update();
                goal.update();
                player.movingPlatforms(actualPositionX);
            } else
            playerPoint.x++;
            steps++;
        }


    }

    public void gravity() {
        if (playerPoint.y < Constants.SCREEN_HEIGHT - 50)
            playerPoint.y += platformManager.canIGoDown(playerPoint);
    }


    @Override
    public void receiveTouch(MotionEvent event) {
        //movingId = 0;
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (upArrow.contains((int) event.getX(), (int) event.getY()))
                    jumping();
                if (leftArrow.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    isGoingLeft = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                if (rightArrow.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    isGoingRight = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                break;

            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
                if (movingId == event.getPointerId(event.getActionIndex())) {
                    isGoingRight = false;
                    isGoingLeft = false;
                }
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        background.draw(canvas);

        player.draw(canvas);
        platformManager.draw(canvas);
        obstacleManager.draw(canvas);
        goal.draw(canvas);

        Paint arrowPaint = new Paint();
        arrowPaint.setColor(Color.rgb(255, 125, 125));
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
            paint.setColor(Color.RED);
            drawCenterText(canvas, paint, "Game Over");
        }
        if (goalReached){
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
            drawCenterText(canvas, paint, "Good job");
        }
    }

    @Override
    public void update() {
        goingLeft();
        goingRight();
        gravity();
        if (!gameOver && !goalReached) {
            player.update(playerPoint, isGoingRight,isGoingLeft);
            //obstacleManager.update();
            if (obstacleManager.playerCollide(player)) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
            if (goal.playerCollideGoal(player)) {
                goalReached = true;
                gameOverTime = System.currentTimeMillis();
            }
        }
        if (gameOver && System.currentTimeMillis() - gameOverTime > 2000) {
            reset();
            gameOver = false;
        }
        if (goalReached && System.currentTimeMillis() - gameOverTime > 1000){
            nextLevel();
            goalReached = false;
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
