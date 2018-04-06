package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.LaserShot;
import com.olivierpicard.crachit.MovingItem;
import com.olivierpicard.crachit.ProgressBar;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public abstract class Shuttle extends MovingItem {
    class Stats {
        class ShootStats {
            public int prob_success_fire;
            public double delta_time_to_shoot;
            private double time_since_last_shoot;

            ShootStats(double deltaTimeToShoot, int probSuccessFire) {
                this.prob_success_fire = probSuccessFire;
                this.delta_time_to_shoot = deltaTimeToShoot;
                this.time_since_last_shoot = 0;
            }

            ShootStats() {
                this.prob_success_fire = 0;
                this.delta_time_to_shoot = 0;
                this.time_since_last_shoot = 0;
            }

            boolean canShoot(double currentTime) {
                if(currentTime - this.time_since_last_shoot < this.delta_time_to_shoot) return false;
                this.time_since_last_shoot = currentTime;
                if(GInterval.random(0, 100) > this.prob_success_fire) return false;
                return true;
            }
        }

        int attack;
        int defense;
        ShootStats shoot_stats;

        Stats(int defense, int attack, ShootStats shootStats) {
            this.defense = defense;
            this.attack = attack;
            this.shoot_stats = shootStats;
        }

        Stats(Stats stats) {
            this.defense = stats.defense;
            this.attack = stats.attack;
            this.shoot_stats = stats.shoot_stats;
        }

        Stats() {
            this.defense = 0;
            this.attack = 0;
            this.shoot_stats = new ShootStats();
        }
    }

    public Stats stats;
    public ProgressBar lifeBar;
    public boolean enable_collision;


    Shuttle(int bitmapRessourceID, int color, Stats stats) {
        super(bitmapRessourceID,
                color,
                new GSize(45, 40),
                1.5f,
                new GVector(0, -1));

        this.stats = stats;
        this.enable_collision = true;
        this.lifeBar = new ProgressBar(this.stats.defense,
                new GSize(this.getSize().width + this.getSize().width/3, 5));
        this.setZPosition(900);
        this.addChild(this.lifeBar);

    }


//    public void shoot(GVector direction, float rotation) {
//
//    }
//
//    /** Crée un rayon laser, à l'emplacement du vaisseau, et l'ajoute à la scene */
//    func shoot(direction: CGVector, rotation: CGFloat) {
//        let laser = LaserShot(shooter: self, color: self.color, direction: direction, rotation: rotation)
//        laser.position = self.position
//        self.scene!.addChild(laser)
//    }


//    public abstract void inCollisionWith(item: Collisionable);
}

