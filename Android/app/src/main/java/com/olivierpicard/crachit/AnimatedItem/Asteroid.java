package com.olivierpicard.crachit.AnimatedItem;

import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.ICollisionable;
import com.olivierpicard.crachit.LaserShot;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;
import com.olivierpicard.crachit.Tools;


/**
 * Le nom de la class est assez représentatif
 * Created by olivierpicard on 06/04/2018.
 */

public class Asteroid  extends AnimatedItem implements ICollisionable {
    public boolean enable_collision;
    public final int dammage = 50;


    public Asteroid(GSize size) {
        super(Tools.getListOfDrawableRessouce("asteroid_", 1, 64),
                0, size, (long)(0.1*1000), false);
        this.enable_collision = true;
        this.setZPosition(800);

    }


    @Override
    public boolean isCollisionEnabled() {
        return this.enable_collision;
    }

    @Override
    public void setCollisionEnable(boolean value) { this.enable_collision = value; }

    @Override
    public void inCollisionWith(ICollisionable item) {
        boolean isDestroy = false;
        if(item instanceof LaserShot
                && ((LaserShot) item).shooter instanceof ShuttlePlayer)
            isDestroy = true;
        else if(item instanceof ShuttlePlayer) isDestroy = true;

        if(isDestroy && getScene() != null) {
            if(this.getScene() instanceof GameScene) {
                final GameScene gameScene = (GameScene) this.getScene();
                gameScene.setScore(gameScene.getScore() + 1);
            }
            final GPoint pos = this.getPosition();
            getScene().addChild(new Explosion(this.getPosition()));
            getScene().removeChild(this);
        }
    }
}
