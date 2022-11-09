package com.example.szakdolgozat;

import android.graphics.Canvas;
import android.graphics.Rect;

import java.util.ArrayList;

public class EnemyManager {
    private ArrayList<Enemy> enemies;
    private SpriteSheet spriteSheet;


    public EnemyManager(int[] level, ProjectileManager projectileManager) {
        enemies = new ArrayList<>();
        spriteSheet = new SpriteSheet();
        Animator animator = new Animator(spriteSheet.getSpriteArray());

        for (int i = 0; level.length - 1 > i; i += 5) {
            enemies.add(new Enemy(level[i], level[i + 1], level[i + 2], animator, projectileManager));
        }
    }

    public Enemy projectileCollideEnemy(Projectile projectile) {
        for (Enemy nmy : enemies)
            if (Rect.intersects(nmy.getEnemy(), projectile.getRect()))
                return nmy;
        return null;
    }

    public void enemyDie(Enemy enemy, Key key) {
        if (enemies.size() == 1) {
            key.setCoords(enemy.getEnemy().left, enemy.getEnemy().top);
            key.setVisible();
        }
        enemies.remove(enemy);
    }


    public void draw(Canvas canvas) {
        for (Enemy nmy : enemies)
            nmy.draw(canvas);
    }

    public void update(int actPosX) {
        for (Enemy nmy : enemies)
            nmy.update(actPosX);
    }

    public void floatLeft() {
        for (Enemy nmy : enemies) {
            nmy.getEnemy().left--;
            nmy.getEnemy().right--;
        }
    }
}