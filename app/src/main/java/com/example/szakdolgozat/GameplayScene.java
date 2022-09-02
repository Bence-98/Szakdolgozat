package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.concurrent.TimeUnit;

public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private int isGoing;
    private Rect upArrow, leftArrow, rightArrow;
    private int szin =200;
    private boolean isGoingLeft, isGoingRight;



    private boolean movingPlayer = false;

    private boolean gameOver = false;
    private long gameOverTime;

    public GameplayScene(){
        player = new Player(new Rect(100,100,200,200), Color.rgb(0,0,0));
        playerPoint = new Point(Constants.SCREEN_WIDTH /2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);

        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);

    }

    public void reset() {
        playerPoint = new Point(Constants.SCREEN_WIDTH /2, 3*Constants.SCREEN_HEIGHT/4);
        player.update(playerPoint);
        obstacleManager = new ObstacleManager(200, 350, 75, Color.BLACK);
        movingPlayer = false;
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }


    public void isMoving(){
        if(isGoing != 0) {
            szin = 100;
            switch (isGoing){
                case 2:
                    playerPoint.x -= 20;
                    break;
                case 3:
                    playerPoint.x += 20;
            }
        }else szin = 200;
    }

   public void jumping() {
        for (int i = 0; i < 10; i++){
            try {
                wait(10);
            } catch (Exception e) {e.printStackTrace();}
           playerPoint.y -= 10;}
    }

    public void goingLeft(){
        if (isGoingLeft)
            playerPoint.x -= 20;

    }

    public void goingRight(){
        if (isGoingRight)
            playerPoint.x += 20;
    }

    public void gravity(){
        if (playerPoint.y < Constants.SCREEN_HEIGHT - 50)
            playerPoint.y += 10;

    }

/*    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (!gameOver && player.getRectangle().contains((int)event.getX(), (int)event.getY()))
                    movingPlayer = true;
                if (gameOver && System.currentTimeMillis() - gameOverTime >= 2000){
                    reset();
                    gameOver = false;
                }
                break;
            case MotionEvent.ACTION_MOVE:
                if (!gameOver && movingPlayer)
                    playerPoint.set((int)event.getX(), (int)event.getY());
                break;
            case MotionEvent.ACTION_UP:
                movingPlayer = false;
                break;
        }
    }*/

    @Override
    public void receiveTouch(MotionEvent event){

        int pointerIndex = event.getActionIndex();
        int maskedAction = event.getActionMasked();

        switch (maskedAction) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (upArrow.contains((int) event.getX(pointerIndex), (int) event.getY(pointerIndex)))
                    jumping();
                if (leftArrow.contains((int)event.getX(pointerIndex), (int)event.getY(pointerIndex)))
                    isGoingLeft = true;
                if (rightArrow.contains((int)event.getX(pointerIndex), (int)event.getY(pointerIndex)))
                    isGoingRight =true;
                break;


            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_POINTER_UP:
            case MotionEvent.ACTION_CANCEL:
                    isGoing = 0;
                    isGoingRight = false;
                    isGoingLeft = false;
                    szin=200;
                break;
        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        player.draw(canvas);
        obstacleManager.draw(canvas);



        Paint arrowPaint = new Paint();
        arrowPaint.setColor(Color.rgb(szin,szin,szin));
        upArrow = new Rect(2000,850,2150,1000);
        leftArrow = new Rect(50,850,200,1000);
        rightArrow = new Rect(250,850,400,1000);
        canvas.drawRect(upArrow,arrowPaint);
        canvas.drawRect(leftArrow,arrowPaint);
        canvas.drawRect(rightArrow,arrowPaint);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    @Override
    public void update() {
        //isMoving();
        goingLeft();
        goingRight();
        gravity();
        if (!gameOver) {
            player.update(playerPoint);
            obstacleManager.update();
            if (obstacleManager.playerCollide(player)){
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
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
