package com.example.szakdolgozat;

import android.graphics.Rect;

import java.util.ArrayList;

public class CollisionDetection {
    private final PlatformManager platformManager;
    private final ObstacleManager obstacleManager;
    private final Goal goal;
    private final ProjectileManager projectileManager;
    private final EnemyManager enemyManager;
    private final Player player;
    private final ArrayList<Projectile> projectiles;

    public CollisionDetection(PlatformManager platformManager, ObstacleManager obstacleManager, Goal goal, ProjectileManager projectileManager, EnemyManager enemyManager, Player player) {
        this.platformManager = platformManager;
        this.obstacleManager = obstacleManager;
        this.goal = goal;
        this.projectileManager = projectileManager;
        this.enemyManager = enemyManager;
        this.player = player;
        this.projectiles = projectileManager.getArray();
    }

    public void bulletCollision(Key key) {
        for (Projectile prtl : projectiles) {
            if (platformManager.projectileCollidePlatform(prtl) || obstacleManager.projectileCollidePlatform(prtl) || Rect.intersects(goal.getRectangle(), prtl.getRect()))
                projectileManager.deleteProjectile(prtl);
            if (enemyManager.projectileCollideEnemy(prtl) != null && prtl.getWhose()) {
                enemyManager.enemyDie(enemyManager.projectileCollideEnemy(prtl), key);
                projectileManager.deleteProjectile(prtl);
            }
            if (Rect.intersects(player.getRectangle(), prtl.getRect()) && !prtl.getWhose()) {
                projectileManager.deleteProjectile(prtl);
                player.setAlive(false);
            }
        }
    }

    public void update(Key key) {
        if (!projectiles.isEmpty())
            bulletCollision(key);
    }
}
