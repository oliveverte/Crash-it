package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.AnimatedItem.Explosion;
import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.ICollisionable;
import com.olivierpicard.crachit.LaserShot;
import com.olivierpicard.crachit.Tools;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ShuttleEnemy extends Shuttle {
    final ShuttlePlayer target;
    double deltaTime_check_targetPosition;
    private double previous_time_updatedDirection;


    ShuttleEnemy(int bitmapRessourceID, int color, Stats stats, ShuttlePlayer target) {
        super(bitmapRessourceID, color, stats);
        this.target = target;
        this.deltaTime_check_targetPosition = 2;
        this.previous_time_updatedDirection = 0;
        this.speed_factor = 2f;
        this.direction = GVector.down();
        this.lifeBar.setPosition(new GPoint(this.getPosition().x,
                this.getPosition().y - this.getSize().height/2
                        - this.lifeBar.getSize().height/2 - 8));
    }


    @Override
    public void update(long currentTime) {
        super.update(currentTime);
        if(this.stats.shoot_stats.canShoot(currentTime))
            shoot(this.direction, this.getZRotation());

        if(this.getScene() != null
                && currentTime - this.previous_time_updatedDirection >= this.deltaTime_check_targetPosition
                && target.getPosition().y - this.getPosition().y > this.target.getPosition().y - Tools.screenMetrics.heightPixels/15)
        {
            this.previous_time_updatedDirection = currentTime;
            updateDirectionToTarget();
        }
    }


    public void updateDirectionToTarget() {
        final float len_BA = this.getPosition().y - target.getPosition().y;
        final float len_CA = target.getPosition().x - this.getPosition().x;
        final float angle = (float)Math.atan(len_CA/len_BA);
        setZRotation((float)(angle*180/Math.PI));
        this.direction = new GVector(-Math.sin(angle), Math.cos(angle));
    }


    @Override
    public void inCollisionWith(ICollisionable item) {
        int increaseScore = 0;
        if(item instanceof LaserShot) {
            final LaserShot laser = (LaserShot)item;
            if(laser.shooter instanceof ShuttleEnemy) return;
            this.lifeBar.setValue(this.lifeBar.getValue() - laser.shooter.stats.attack);
//              TODO : increaseScore
//            if(this.lifeBar.getValue() == 0) increaseScore += 1;
        } else if(item instanceof ShuttlePlayer) {
            this.lifeBar.setValue(0);
            increaseScore += 1;
        }

        if(increaseScore > 0) {
            final GameScene scene = (GameScene)getScene();
//            TODO : Remplir la class Explosion
//            scene.addChild(new Explosion(this.getPosition()));
            this.getScene().removeChild(this);
        }
    }


}
