package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.Shuttle.Shuttle;
import com.olivierpicard.crachit.Shuttle.ShuttleEnemy;

/**
 * Représente un rayon laser
 * Created by olivierpicard on 06/04/2018.
 */

public class LaserShot extends MovingItem implements ICollisionable {
    public final Shuttle shooter;
    private boolean enable_collision;



    public LaserShot(Shuttle shooter, int color, GVector direction, float rotation) {
        super(null, color, new GSize(3, 20), 8f, direction);
        this.shooter = shooter;
        this.enable_collision = true;
        this.setColor(Tools.setColorOpacity(color, 102));
        this.setZRotation(rotation);
    }


    @Override
    public void inCollisionWith(ICollisionable item) {
        if(!(item instanceof Shuttle)) return;

        final Shuttle target = (Shuttle)item;
        if(this.shooter instanceof ShuttleEnemy && target instanceof ShuttleEnemy) { return; }
        if(item == shooter) return;
        if(getScene() != null)
            getScene().removeChild(this);
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
