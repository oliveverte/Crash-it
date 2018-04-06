package com.olivierpicard.crachit;

import android.graphics.Color;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.IGUpdatable;

import java.util.ArrayList;
import java.util.List;

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

    public void didInitialized() {
        state = GameState.welcome;
        this.starsGenerator_topLayer = new StarsGenerator(this, 8,
                new GInterval(50, 80),
                new GSize(1,2),
                2);
        this.starsGenerator_bottomLayer = new StarsGenerator(this, 13,
                new GInterval(40, 60),
                new GSize(1,1),
                1);

    }

    public void start() {

    }

    @Override
    public void update(Double currentTime) {
        this.starsGenerator_topLayer.generate();
        this.starsGenerator_bottomLayer.generate();
        List<GNode> itemToDelete = new ArrayList<>();
        for(GNode node : children) {
            if(!(node instanceof MovingItem)) continue;
            MovingItem movingItem = (MovingItem) node;
            movingItem.update(currentTime);
            if(movingItem.isOutOfScreen())
                itemToDelete.add(node);
        }
        removeChildren(itemToDelete);
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
