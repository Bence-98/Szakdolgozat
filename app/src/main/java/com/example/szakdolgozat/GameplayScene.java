package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.view.MotionEvent;

public class GameplayScene implements Scene {

    private final Rect r = new Rect();
    private final Player player;
    private final Point playerPoint;
    private ObstacleManager obstacleManager;
    private boolean isGoingLeft, isGoingRight;
    private PlatformManager platformManager;
    private final LevelCoords levelCoords;
    private int currLvlStartingLine = -6;
    private Goal goal;
    private int movingId = 0;
    private int actualPositionX;
    private final Background background;
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
    private final int lastlevel;

    public GameplayScene() {
        player = new Player(new Rect(100, 100, 200, 200));
        playerPoint = new Point(200, 440);
        levelCoords = new LevelCoords();
        background = new Background();
        nextLevel();
        lastlevel = levelCoords.getLength() / 6;
    }


    public void nextLevel() {
        currLvlStartingLine += 6;
        level++;
        int[] playerStartingPoint = levelCoords.getCoords(currLvlStartingLine);
        playerPoint.set(playerStartingPoint[0], playerStartingPoint[1]);
        player.update(playerPoint);
        background.reset();
        actualPositionX = 200;
        keyPicked = false;
        hud = new HUD();
        key = new Key();

        int[] currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 1);
        platformManager = new PlatformManager(currLvlCoords);
        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 2);
        obstacleManager = new ObstacleManager(currLvlCoords);
        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 4);
        goal = new Goal(currLvlCoords);
        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 5);
        lavaManager = new LavaManager(currLvlCoords);
        projectileManager = new ProjectileManager();
        currLvlCoords = levelCoords.getCoords(currLvlStartingLine + 3);
        enemyManager = new EnemyManager(currLvlCoords, projectileManager);
        collisionDetection = new CollisionDetection(platformManager, obstacleManager, goal, projectileManager, enemyManager, player);
    }


    public void reset() {
        currLvlStartingLine -= 6;
        level--;
        nextLevel();
        player.setAlive(true);
    }

    @Override
    public void terminate() {
        SceneManager.ACTIVE_SCENE = 0;
    }

    public void jumping() {
        if (platformManager.canIGoDown(playerPoint) == 0)
            for (int i = 0; i < 30; i++) {
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
                if (playerPoint.x > 750 && actualPositionX <= 10200 - Constants.SCREEN_WIDTH) {
                    actualPositionX++;
                    background.update(actualPositionX);
                    platformManager.update();
                    key.update();
                    lavaManager.update();
                    obstacleManager.update();
                    enemyManager.floatLeft();
                    goal.update();
                    projectileManager.floatLeft();
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
        if (player.getAlive()) {
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
        } else if (direction)
            state = PlayerState.DEATH_RIGHT.ordinal();
        else state = PlayerState.DEATH_LEFT.ordinal();
    }


    @Override
    public void receiveTouch(MotionEvent event) {
        switch (event.getActionMasked()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_POINTER_DOWN:
                if (hud.getupArrow().contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    jumping();
                }
                if (hud.getleftArrow().contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    direction = false;
                    isGoingLeft = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                if (hud.getrightArrow().contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    direction = true;
                    isGoingRight = true;
                    movingId = event.getPointerId(event.getActionIndex());
                }
                if (hud.getfireButton().contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex()))) {
                    if (lastFire < System.currentTimeMillis() - 1000) {
                        if (player.getDirection())
                            projectileManager.fire(playerPoint.x + 50, playerPoint.y + 5, player.getDirection(), true);
                        else
                            projectileManager.fire(playerPoint.x - 50, playerPoint.y + 5, player.getDirection(), true);
                        lastFire = System.currentTimeMillis();
                    }
                }
                if (hud.getRestartButton().contains((int) event.getX(event.getActionIndex()), (int) event.getY(event.getActionIndex())))
                    if (!gameOver)
                        reset();
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
        projectileManager.draw(canvas);
        platformManager.draw(canvas);
        obstacleManager.draw(canvas);
        goal.draw(canvas, keyPicked);
        enemyManager.draw(canvas);
        lavaManager.draw(canvas);
        player.draw(canvas);
        hud.draw(canvas, keyPicked);
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
        player.setPlayerState(state);
        if (!gameOver && !goalReached && !win) {
            player.update(playerPoint);
            projectileManager.update();
            collisionDetection.update(key);
            if (key.playerCollideKey(player))
                keyPicked = true;
            enemyManager.update(actualPositionX);
            lavaManager.changeWave();
            if (obstacleManager.playerCollide(player) || lavaManager.playerCollideLava(player) || playerPoint.y + 50 > Constants.SCREEN_HEIGHT || !player.getAlive()) {
                gameOver = true;
                gameOverTime = System.currentTimeMillis();
                player.setAlive(false);
            }
            if (goal.playerCollideGoal(player) && keyPicked) {
                if (level == lastlevel) {
                    win = true;
                } else
                    goalReached = true;

                gameOverTime = System.currentTimeMillis();
            }
        }
        if (gameOver && System.currentTimeMillis() - gameOverTime > 1500) {
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
