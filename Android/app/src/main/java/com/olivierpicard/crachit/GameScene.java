package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Shuttle.ShuttleEnemiesGenerator;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;

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

    private final float PLAYER_MOVING_SPEED = 3;
    private GameState state;
    private StarsGenerator starsGenerator_topLayer;
    private StarsGenerator starsGenerator_bottomLayer;
    private ShuttleEnemiesGenerator shuttle_enemy_generator;
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
        this.shuttle_enemy_generator = new ShuttleEnemiesGenerator(this, player);
        start();
    }

    public void start() {
        this.shuttle_enemy_generator.enable = true;
        state = GameState.play;
    }

    public void gameOver() {

    }


    @Override
    public void update(long currentTime) {
        this.starsGenerator_topLayer.generate();
        this.starsGenerator_bottomLayer.generate();
        this.shuttle_enemy_generator.generate(currentTime);
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
        if(this.state == GameState.play) {
            if(pos.x < Tools.screenMetrics.widthPixels/2){
                player.direction.dx = -PLAYER_MOVING_SPEED;
            } else if(pos.x > Tools.screenMetrics.widthPixels/2) {
                player.direction.dx = PLAYER_MOVING_SPEED;
            }
        }
    }

    @Override
    public void touchUp(GPoint pos) {
        super.touchUp(pos);
        if(this.state == GameState.play) player.direction.dx = 0;
//        else if(this.state == GameState.welcome) { this.welcome_screen.touchUp(pos); }
//        else { gameOver_screen.touchUp(pos) }
    }

}
