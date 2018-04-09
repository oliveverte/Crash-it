package com.olivierpicard.crachit;

import android.graphics.Color;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.IGUpdatable;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 *
 * Created by olivierpicard on 03/04/2018.
 */

public class GameScene extends GScene {
    public enum GameState {
        play,
        gameOver,
        welcome
    }

    private GameState state;
    private StarsGenerator starsGenerator_topLayer;
    private StarsGenerator starsGenerator_bottomLayer;
    private ShuttlePlayer player;

    public void didInitialized() {
        state = GameState.welcome;

        this.player = new ShuttlePlayer();
        this.player.setPosition(Tools.fromSceneToScreenPos(new GPoint(0.5f, 0.2f)));
        addChild(player);

        this.starsGenerator_topLayer = new StarsGenerator(this, 8,
                new GInterval(50, 80),
                new GSize(1,2),
                1f);
        this.starsGenerator_bottomLayer = new StarsGenerator(this, 13,
                new GInterval(40, 60),
                new GSize(1,1),
                0.4f);

    }

    public void start() {

    }

    public void gameOver() {

    }


    @Override
    public void update(Double currentTime) {
        this.starsGenerator_topLayer.generate();
        this.starsGenerator_bottomLayer.generate();

        for(GNode node : children) {
            if(!(node instanceof MovingItem)) continue;
            MovingItem movingItem = (MovingItem) node;
            movingItem.update(currentTime);
            if(movingItem.isOutOfScreen())
                removeChild(node);
            else if(node instanceof ICollisionable) {
                final ICollisionable collisionableItem = (ICollisionable) node;
                final List<ICollisionable> overlapsedItems = overlapsListItems(collisionableItem);
                for(ICollisionable ovItem : overlapsedItems) {
                    collisionableItem.inCollisionWith(ovItem);
                    ovItem.inCollisionWith(collisionableItem);
                }
            }
        }
    }


    private List<ICollisionable> overlapsListItems(ICollisionable item) {
        List<ICollisionable> list = new ArrayList<>();
        for(GNode node : this.children) {
            if(!(node instanceof ICollisionable)) continue;
            final ICollisionable child = (ICollisionable) node;
            if(item == child) continue;
            if(item.isOverlaps(child)) list.add(child);
        }
        return list;
    }




    @Override
    public void touchDown(GPoint pos) {
        super.touchDown(pos);
    }

    @Override
    public void touchUp(GPoint pos) {
        super.touchUp(pos);
    }

}
