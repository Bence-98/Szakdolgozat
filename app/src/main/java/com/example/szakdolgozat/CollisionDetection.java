package com.example.szakdolgozat;

import android.graphics.Rect;

import java.util.ArrayList;

public class CollisionDetection {
    private PlatformManager platformManager;
    private ObstacleManager obstacleManager;
    private Goal goal;
    private ProjectileManager projectileManager;
    private EnemyManager enemyManager;


    private ArrayList<Projectile> projectiles;

    public CollisionDetection(PlatformManager platformManager, ObstacleManager obstacleManager, Goal goal, ProjectileManager projectileManager, EnemyManager enemyManager) {
        this.platformManager = platformManager;
        this.obstacleManager = obstacleManager;
        this.goal = goal;
        this.projectileManager = projectileManager;
        this.enemyManager = enemyManager;

        this.projectiles = projectileManager.getArray();
    }

    public void bulletCollision() {
        for (Projectile prtl : projectiles) {
            if (platformManager.projectileCollidePlatform(prtl) || obstacleManager.projectileCollidePlatform(prtl) || Rect.intersects(goal.getRectangle(), prtl.getRect()))
                projectileManager.deleteProjectile(prtl);
            if (enemyManager.profectileCollideEnemy(prtl) != null) {
                enemyManager.die(enemyManager.profectileCollideEnemy(prtl));
                projectileManager.deleteProjectile(prtl);
            }

        }
    }

    public void update() {
        if (!projectiles.isEmpty())
            bulletCollision();
    }

}
