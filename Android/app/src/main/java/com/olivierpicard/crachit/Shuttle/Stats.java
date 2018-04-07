package com.olivierpicard.crachit.Shuttle;

/**
 * Created by olivierpicard on 07/04/2018.
 */

public class Stats {
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
