package com.olivierpicard.crachit;

import android.graphics.Color;
import android.util.Log;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GTools;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.Shuttle.ShuttleEnemiesGenerator;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;

import java.util.ArrayList;
import java.util.List;

/**
 * Scène(vue) dans lequel le jeu prend vie
 * Created by olivierpicard on 03/04/2018.
 */

public class GameScene extends GScene {
    public enum GameState {
        play,
        gameOver,
        welcome
    }

    private final float PLAYER_MOVING_SPEED = 3f;
    private GLabel score_label;
    private GameState state;
    private StarsGenerator starsGenerator_topLayer;
    private StarsGenerator starsGenerator_bottomLayer;
    private ShuttleEnemiesGenerator shuttle_enemy_generator;
    private AsteroidsGenerator asteroids_generator;
    private ShuttlePlayer player;
    private WelcomeScreen welcomeScreen;
    private GameOverScreen gameOver_screen;


    public int getScore() {
        return Integer.valueOf(this.score_label.getText());
    }

    public void setScore(int score) {
        this.score_label.setText(Integer.toString(score));
    }


    public void didInitialized() {
        System.out.println(this.getSize());
        this.player = new ShuttlePlayer();
        this.player.setPosition(GTools.fromSceneToScreenPos(this.getSize(), new GPoint(0.5f, 0.2f)));
        this.starsGenerator_topLayer = new StarsGenerator(this, 8,
                new GInterval(50, 80),
                new GSize(1,2),
                1f);
        this.starsGenerator_bottomLayer = new StarsGenerator(this, 13,
                new GInterval(40, 60),
                new GSize(1,1),
                0.4f);
        this.shuttle_enemy_generator = new ShuttleEnemiesGenerator(this, player);

        this.asteroids_generator = new AsteroidsGenerator(this);

        this.score_label = new GLabel("0");
        this.score_label.setFontSize(55);
        this.score_label.setColor(Color.WHITE);
        this.score_label.setAlpha(125);
        this.score_label.setHidden(true);
        this.score_label.setPosition(GTools.fromSceneToScreenPos(this.getSize(), new GPoint(0.5f, 0.8f)));
        addChild(this.score_label);

        this.gameOver_screen = new GameOverScreen(this);
        this.welcomeScreen = new WelcomeScreen(this);
        this.welcomeScreen.show();
        this.state = GameState.welcome;

    }


    public void start() {
        setScore(0);
//        this.view_Controller.initWithScore = nil
        // TODO : Tuto image
//        this.tutoImage = TutoImage(this)
        this.addChild(player);
        this.state = GameState.play;
        this.score_label.setHidden(false);
        this.player.direction = GVector.zero();
        this.asteroids_generator.enable = true;
        this.shuttle_enemy_generator.enable = true;
        this.player.lifeBar.setValue(this.player.stats.defense);
        this.player.setPosition(GTools.fromSceneToScreenPos(this.getSize(), new GPoint(0.5f, 0.2f)));
    }


    public void gameOver() {
        this.shuttle_enemy_generator.enable = false;
        this.asteroids_generator.enable = false;
        this.score_label.setHidden(true);
        this.state = GameState.gameOver;
        this.gameOver_screen.show();
//        TODO : tutoImage
//        this.tutoImage = nil;
    }


    @Override
    public void update(long currentTime) {
        this.starsGenerator_topLayer.generate();
        this.starsGenerator_bottomLayer.generate();
        this.shuttle_enemy_generator.generate(currentTime);
        this.asteroids_generator.generate(currentTime);

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
            if(pos.x < this.getSize().width/2){
                player.direction.dx = -PLAYER_MOVING_SPEED;
            } else if(pos.x > this.getSize().width/2) {
                player.direction.dx = PLAYER_MOVING_SPEED;
            }
        }
    }

    @Override
    public void touchUp(GPoint pos) {
        super.touchUp(pos);
        if(this.state == GameState.play) player.direction.dx = 0;
        else if(this.state == GameState.welcome) this.welcomeScreen.touchUp(pos);
        else gameOver_screen.touchUp(pos);
    }


    public void switchScreen(GameState state) {
        this.state = state;
        if(state == GameState.gameOver) this.gameOver_screen.show();
        else if(state == GameState.welcome) this.welcomeScreen.show();
    }





}



















