package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.ICollisionable;
import com.olivierpicard.crachit.LaserShot;
import com.olivierpicard.crachit.MovingItem;
import com.olivierpicard.crachit.ProgressBar;

/**
 * Représente un vaisseau non spécialisé, (joueur, ennemie...)
 * Created by olivierpicard on 06/04/2018.
 */

public abstract class Shuttle extends MovingItem implements ICollisionable {
    public Stats stats;
    public ProgressBar lifeBar;
    public boolean enable_collision;


    Shuttle(int bitmapRessourceID, int color, Stats stats) {
        super(bitmapRessourceID,
                color,
                new GSize(45, 35),
                1.5f,
                new GVector(0, -1));

        this.stats = stats;
        this.enable_collision = true;
        this.lifeBar = new ProgressBar(this.stats.defense,
                new GSize(this.getSize().width + this.getSize().width/3, 5));
        this.setZPosition(900);
        this.addChild(this.lifeBar);

    }


    public abstract void inCollisionWith(ICollisionable item);

    public void shoot(GVector direction, float rotation) {
        final LaserShot laser = new LaserShot(this, getColor(), direction, rotation);
        laser.setPosition(getPosition());
        getScene().addChild(laser);
    }

    @Override
    public boolean isCollisionEnabled() {
        return this.enable_collision;
    }

    @Override
    public void setCollisionEnable(boolean value) {
        this.enable_collision = value;
    }


}

