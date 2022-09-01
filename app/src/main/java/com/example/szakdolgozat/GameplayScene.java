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
    private int szin =200;

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
                case 1:
                    playerPoint.y -= 20;
                    break;
                case 2:
                    playerPoint.x -= 20;
                    break;
                case 3:
                    playerPoint.x += 20;
            }
        }else szin = 200;
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

/*        if (upArrow.contains((int)event.getX(), (int)event.getY()))
            isGoing = 1;*/

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if (upArrow.contains((int)event.getX(), (int)event.getY()))
                    isGoing = 1;
                if (leftArrow.contains((int)event.getX(), (int)event.getY()))
                    isGoing = 2;
                if (rightArrow.contains((int)event.getX(), (int)event.getY()))
                    isGoing = 3;
                break;

            case MotionEvent.ACTION_UP:
                    isGoing = 0;
                    szin=200;
                break;

        }
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(Color.WHITE);

        player.draw(canvas);
        obstacleManager.draw(canvas);



        Paint paintt = new Paint();
        paintt.setColor(Color.rgb(szin,szin,szin));
        upArrow = new Rect(2000,850,2150,1000);
        leftArrow = new Rect(50,850,200,1000);
        rightArrow = new Rect(250,850,400,1000);
        canvas.drawRect(upArrow,paintt);
        canvas.drawRect(leftArrow,paintt);
        canvas.drawRect(rightArrow,paintt);

        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
            drawCenterText(canvas, paint, "Game Over");
        }
    }

    @Override
    public void update() {
        isMoving();
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
