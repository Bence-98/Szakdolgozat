package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

import java.util.ArrayList;
import java.util.List;


public class GameplayScene implements Scene {

    private Rect r = new Rect();

    private Player player;
    private Point playerPoint;
    private ObstacleManager obstacleManager;
    private Rect upArrow, leftArrow, rightArrow, fireButton;
    private boolean isGoingLeft, isGoingRight;
    private PlatformManager platformManager;
    private LevelCoords levelCoords;
    private int currLvlStartingLine = -5;
    private int[] currLvlCoords;
    private Goal goal;
    private int movingId = 0;
    private int actualPositionX;
    private Background background;
    private ProjectileManager projectileManager;
    private CollisionDetection collisionDetection;
    private long lastFire;
    private EnemyManager enemyManager;
    private boolean direction = true;
    private Key key;
    private HUD hud;
    private boolean keyPicked;
    private int level;
    private boolean win = false;

    private boolean goalReached = false;
    private boolean gameOver = false;
    private long gameOverTime;
    private int state = 2;
    private LavaManager lavaManager;


    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200), Color.rgb(0, 0, 0));
        playerPoint = new Point(200, 440);

        levelCoords = new LevelCoords();
        background = new Background();
        nextLevel();


    }


    public void nextLevel() {
        playerPoint.set(200, 440);
        //player.update(playerPoint, true, isGoingLeft);
        player.update(playerPoint);
        currLvlStartingLine += 5;
        background.reset();
        actualPositionX = 0;
        keyPicked = false;
        hud = new HUD();
        key = new Key();
        level++;

        currLvlCoords = levelCoords.getCoords(currLvlStartingLine);
        platformManager = new PlatformManager(currLvlCoords);

        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 1);
        obstacleManager = new ObstacleManager(currLvlCoords);


        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 3);
        goal = new Goal(currLvlCoords, Color.GREEN);

        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 4);
        lavaManager = new LavaManager(currLvlCoords);

        projectileManager = new ProjectileManager();

        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 2);
        enemyManager = new EnemyManager(currLvlCoords, projectileManager);


        collisionDetection = new CollisionDetection(platformManager, obstacleManager, goal, projectileManager, enemyManager, player);

    }


    public void reset() {
        currLvlStartingLine -= 5;
        level--;
        nextLevel();
        player.playerDie(false);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }


    public void jumping() {
        if (platformManager.canIGoDown(playerPoint) == 0)
            for (int i = 0; i < 25; i++) {
                try {
                    Thread.sleep(5);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if (platformManager.canIGoUp(playerPoint))
                    playerPoint.y -= 15;
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
                if (playerPoint.x > 750 && actualPositionX <= 10000 - Constants.SCREEN_WIDTH) {
                    actualPositionX++;
                    background.update(actualPositionX);
                    platformManager.update();
                    key.update();
                    lavaManager.update();
                    obstacleManager.update();
                    enemyManager.floatLeft();
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


    public void calculateState() {
        if (platformManager.canIGoDown(playerPoint) == 0) {
            if (isGoingRight) {
                state = PlayerState.WALK_RIGHT.ordinal();
            }
            if (isGoingLeft) {
                state = PlayerState.WALK_LEFT.ordinal();
            }
            if (!isGoingRight && !isGoingLeft) {
                if (direction)
                    state = PlayerState.IDLE_RIGHT.ordinal();
                else state = PlayerState.IDLE_LEFT.ordinal();
            }
        } else if (direction)
            state = PlayerState.JUMP_RIGHT.ordinal();
        else state = PlayerState.JUMP_LEFT.ordinal();

    }


    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (upArrow.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    jumping();
                }
                if (leftArrow.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    direction = false;
                    isGoingLeft = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                if (rightArrow.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    direction = true;
                    isGoingRight = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                if (fireButton.contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    if (lastFire < System.currentTimeMillis() - 1000) {
                        if (player.getDirection())
                            projectileManager.fire(playerPoint.x + 50, playerPoint.y + 5, player.getDirection(), true);
                        else
                            projectileManager.fire(playerPoint.x - 50, playerPoint.y + 5, player.getDirection(), true);
                        lastFire = System.currentTimeMillis();
                    }
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

        key.draw(canvas);

        hud.draw(canvas, keyPicked);

        projectileManager.draw(canvas);
        platformManager.draw(canvas);
        obstacleManager.draw(canvas);
        goal.draw(canvas, keyPicked);
        enemyManager.draw(canvas);
        lavaManager.draw(canvas);


        Paint arrowPaint = new Paint();
        arrowPaint.setColor(Color.rgb(255, 125, 125));
        arrowPaint.setAlpha(100);
        upArrow = new Rect(2000, 850, 2150, 1000);
        leftArrow = new Rect(50, 850, 200, 1000);
        rightArrow = new Rect(250, 850, 400, 1000);
        fireButton = new Rect(2000, 650, 2150, 800);
        canvas.drawRect(upArrow, arrowPaint);
        canvas.drawRect(leftArrow, arrowPaint);
        canvas.drawRect(rightArrow, arrowPaint);
        canvas.drawRect(fireButton, arrowPaint);


        player.draw(canvas);
        if (gameOver) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.RED);
            drawCenterText(canvas, paint, "Game Over");
        }
        if (goalReached) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.GREEN);
            drawCenterText(canvas, paint, "Good job");
        }

        if (win) {
            Paint paint = new Paint();
            paint.setTextSize(100);
            paint.setColor(Color.YELLOW);
            drawCenterText(canvas, paint, "You Won");
        }
    }

    @Override
    public void update() {
        goingLeft();
        goingRight();
        gravity();
        calculateState();
        if (!gameOver && !goalReached) {
            player.setPlayerState(state);
            player.update(playerPoint);
            projectileManager.update();
            collisionDetection.update(key);
            if (key.playerCollideKey(player))
                keyPicked = true;
            enemyManager.update();
            lavaManager.changeWave();
            if (obstacleManager.playerCollide(player) || lavaManager.playerCollideLava(player) || playerPoint.y > 1000 || player.playerAlive()) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
            }
            if (goal.playerCollideGoal(player) && keyPicked) {
                if (level == 3) {
                    win = true;
                } else
                    goalReached = true;

                gameOverTime = System.currentTimeMillis();

            }

        }
        if (gameOver && System.currentTimeMillis() - gameOverTime > 2000) {
            reset();
            gameOver = false;
        }
        if (goalReached && System.currentTimeMillis() - gameOverTime > 1000) {
            nextLevel();
            goalReached = false;
        }
        if (win && System.currentTimeMillis() - gameOverTime > 2000) {
            System.exit(0);
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
