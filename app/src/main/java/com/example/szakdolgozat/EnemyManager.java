package com.example.szakdolgozat;

import android.graphics.Canvas;

import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private SpriteSheet spriteSheet;

    public EnemyManager(int[] level) {
        enemies = new ArrayList<>();
        spriteSheet = new SpriteSheet(Constants.CURRENT_CONTEXT);

        for (int i = 0; level.length - 1 > i; i += 5) {
            enemies.add(new Enemy(level[i], level[i + 1], level[i + 2], level[i + 3], level[i+4], spriteSheet.getEnemySprite()));
        }
    }

    public void draw(Canvas canvas){
        for (Enemy nmy: enemies)
            nmy.draw(canvas);
    }

    public void update(){
        for (Enemy nmy: enemies)
            nmy.update();
    }

    public void floatLeft(){
        for (Enemy nmy : enemies){
            nmy.getEnemy().left--;
            nmy.getEnemy().right--;
        }
    }

}