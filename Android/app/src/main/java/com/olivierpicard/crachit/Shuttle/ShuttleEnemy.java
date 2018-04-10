package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.GPoint;
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
        this.lifeBar.setPosition(new GPoint(this.getPosition().x,
                this.getPosition().y + this.getSize().height/2
                        + this.lifeBar.getSize().height/2 + 8));
    }


    @Override
    public void update(long currentTime) {
        super.update(currentTime);
        if(this.stats.shoot_stats.canShoot(currentTime))
            shoot(this.direction, this.getZRotation());
        if(this.getScene() != null
                && currentTime - this.previous_time_updatedDirection >= this.deltaTime_check_targetPosition
                && this.getPosition().y - target.getPosition().y > this.target.getPosition().y + Tools.screenMetrics.heightPixels/15)
        {
            this.previous_time_updatedDirection = currentTime;
//            TODO : Faire la fonction updateDirectionTarget()
//            updateDirectionToTarget();
        }
    }


//    public void updateDirectionToTarget() {
//        final float len_BA = this.getPosition().y - target.getPosition().y;
//        final float len_CA = target.getPosition().x - this.getPosition().x;
//
//    }
//
//    func updateDirectionToTarget() {
//        // Soit un triangle rectangle en A, B est self et C la target
//        let len_BA = self.position.y - target.position.y
//        let len_CA = target.position.x - self.position.x
//
//        let angleRadian = atan(len_CA/len_BA)
//        self.zRotation = angleRadian
//        self.direction = CGVector(dx: sin(angleRadian), dy: -cos(angleRadian))
//
//    }

    @Override
    public void inCollisionWith(ICollisionable item) {
        int increaseScore = 0;
        if(item instanceof LaserShot) {
            final LaserShot laser = (LaserShot)item;
            if(laser.shooter instanceof ShuttleEnemy) return;
            this.lifeBar.setValue(this.lifeBar.getValue() - laser.shooter.stats.attack);
            if(this.lifeBar.getValue() == 0) increaseScore += 1;
        } else if(item instanceof ShuttlePlayer) {
            this.lifeBar.setValue(0);
            increaseScore += 1;
        }

        if(increaseScore > 0) {
            final GameScene scene = (GameScene)getScene();
//            TODO : Remplir la class Explosion
//            scene.addChild(new Explosion());
            this.getScene().removeChild(this);
        }
    }



//
//    override func inCollisionWith(item: Collisionable) {
//        var increaseScore = 0
//        if let laser = item as? LaserShot {
//            if type(of: laser.shooter) == ShuttleEnemy.self { return }
//            self.lifeBar.value -= laser.shooter.stats.attack
//            if self.lifeBar.value == 0 { increaseScore += 1 }
//        } else if item is ShuttlePlayer {
//            self.lifeBar.value = 0
//            increaseScore += 1
//        }
//
//        // L'ennemie(self) est détruit
//        if(increaseScore > 0) {
//            if let gameScene = self.scene as? GameScene {
//                gameScene.score += 2
//            }
////            TODO : Remplir la class Explosion
////            self.scene?.addChild(Explosion(position: self.position))
//            self.scene?.removeChildren(in: [self])
//        }
//    }

}
