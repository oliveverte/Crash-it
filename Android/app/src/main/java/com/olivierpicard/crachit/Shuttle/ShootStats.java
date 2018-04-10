package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GInterval;

/**
 * Created by olivierpicard on 07/04/2018.
 */

public class ShootStats {
    public int prob_success_fire;
    public long delta_time_to_shoot;
    private long time_since_last_shoot;

    ShootStats(long deltaTimeToShoot, int probSuccessFire) {
        this.prob_success_fire = probSuccessFire;
        this.delta_time_to_shoot = deltaTimeToShoot;
        this.time_since_last_shoot = 0;
    }

    ShootStats() {
        this.prob_success_fire = 0;
        this.delta_time_to_shoot = 0;
        this.time_since_last_shoot = 0;
    }

    boolean canShoot(long currentTime) {
        if(currentTime - this.time_since_last_shoot < this.delta_time_to_shoot) return false;
        this.time_since_last_shoot = currentTime;
        return (GInterval.random(0, 100) > this.prob_success_fire);
    }
}
