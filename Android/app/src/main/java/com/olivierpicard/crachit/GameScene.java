package com.olivierpicard.crachit;

import android.graphics.Color;

import com.olivierpicard.crachit.AnimatedItem.Asteroid;
import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GTools;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.Graphics.IGDrawable;
import com.olivierpicard.crachit.Shuttle.Shuttle;
import com.olivierpicard.crachit.Shuttle.ShuttleEnemiesGenerator;
import com.olivierpicard.crachit.Shuttle.ShuttleEnemy;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;

import java.util.ArrayList;
import java.util.List;

import static com.olivierpicard.crachit.DataBaseHandler.*;

/**
 * Scène(vue) dans lequel le jeu prend vie
 * Created by olivierpicard on 03/04/2018.
 */

public class GameScene extends GScene {
    public enum GameState {
        PLAY,
        GAME_OVER,
        WELCOME,
        RESUME
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
    private TutoImage tutoImage;
    public static int scoreToResumeFrom = 0;
    public static List<DataBaseHandler.ItemRestaurationTable> itemsToResumeFrom = null;
    public volatile static GameState flag_stateToSwitchTo = null;


    public int getScore() {
        return Integer.valueOf(this.score_label.getText());
    }

    public void setScore(int score) {
        this.score_label.setText(Integer.toString(score));
    }


    public void didInitialized() {
        this.player = new ShuttlePlayer();
        this.player.setPosition(GTools.fromSceneToScreenPos(this.getSize(), new GPoint(0.5f, 0.2f)));
        this.starsGenerator_topLayer = new StarsGenerator(this, 8,
                new GInterval(60, 80),
                new GSize(2,3),
                1f);
        this.starsGenerator_bottomLayer = new StarsGenerator(this, 10,
                new GInterval(40, 60),
                new GSize(2,2),
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
        if(this.flag_stateToSwitchTo != GameState.RESUME)
            flag_stateToSwitchTo = GameState.WELCOME;

    }


    public void start() {
        reset();


        this.welcomeScreen.hide();
        this.gameOver_screen.hide();
        this.state = GameState.PLAY;
        this.score_label.setHidden(false);
        this.player.direction = GVector.zero();
        this.asteroids_generator.enable = true;
        this.shuttle_enemy_generator.enable = true;
        this.player.lifeBar.setValue(this.player.stats.defense);
        this.player.setPosition(GTools.fromSceneToScreenPos(this.getSize(), new GPoint(0.5f, 0.2f)));
        this.addChild(player);

        if(this.flag_stateToSwitchTo != GameState.RESUME) {
            setScore(0);
            this.tutoImage = new TutoImage(this);
            return;
        }


        setScore(scoreToResumeFrom);
        if(itemsToResumeFrom == null) return;
        for(DataBaseHandler.ItemRestaurationTable item : itemsToResumeFrom) {
            if(item.classType.equals(ShuttleEnemy.class.getSimpleName()))
                this.shuttle_enemy_generator.restaure(item);
            else if(item.classType.equals(Asteroid.class.getSimpleName()))
                this.asteroids_generator.restaure(item);
            else if(item.classType.equals(ShuttlePlayer.class.getSimpleName()))
                this.player.setPosition(new GPoint(item.xPos, item.yPos));
        }
    }


    public void gameOver() {
        this.shuttle_enemy_generator.enable = false;
        this.asteroids_generator.enable = false;
        this.score_label.setHidden(true);
        this.gameOver_screen.savedItems = saveItem();
        flag_stateToSwitchTo = GameState.GAME_OVER;
        if(this.tutoImage != null) {
            this.tutoImage.delete();
            this.tutoImage = null;
        }
    }


    @Override
    public void update(long currentTime) {
        checkFlagGameStateChangeRequest();
        if(this.tutoImage == null) {
            this.shuttle_enemy_generator.generate(currentTime);
            this.asteroids_generator.generate(currentTime);
        }
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
        if(tutoImage != null) {
            this.tutoImage.delete();
            this.tutoImage = null;
        }
        if(this.state == GameState.PLAY) {
            if(pos.x < this.getSize().width/2){
                player.direction.dx = -PLAYER_MOVING_SPEED;
            } else if(pos.x > this.getSize().width/2) {
                player.direction.dx = PLAYER_MOVING_SPEED;
            }
        }
    }

    @Override
    public void touchUp(GPoint pos) {
        if(this.state == GameState.PLAY) player.direction.dx = 0;
        else if(this.state == GameState.WELCOME) this.welcomeScreen.touchUp(pos);
        else gameOver_screen.touchUp(pos);
    }


    private void checkFlagGameStateChangeRequest() {
        if(this.flag_stateToSwitchTo == null) return;

        switch (this.flag_stateToSwitchTo) {
            case PLAY:
            case RESUME:
                start();
                break;
            case WELCOME:
                gameOver_screen.hide();
                welcomeScreen.show();
                break;
            case GAME_OVER:
                welcomeScreen.hide();
                gameOver_screen.show();
                break;
        }
        if(this.flag_stateToSwitchTo == GameState.RESUME)
            this.flag_stateToSwitchTo = GameState.PLAY;
        this.state = flag_stateToSwitchTo;
        flag_stateToSwitchTo = null;
    }


    private void reset() {
        List<GNode> itemToDelete = new ArrayList<>();
        for(GNode node : this.children)
            if(node instanceof ICollisionable)
                itemToDelete.add(node);
        removeChildren(itemToDelete);
    }


    public List<DataBaseHandler.ItemRestaurationTable> saveItem() {
        List<DataBaseHandler.ItemRestaurationTable> items = new ArrayList<>();
        for(GNode node : this.children) {
            if(!(node instanceof Asteroid) && !(node instanceof Shuttle)) continue;
            MovingItem mv = (MovingItem)node;
            DataBaseHandler.ItemRestaurationTable item = DataBaseHandler.reference.new ItemRestaurationTable();
            item.xPos = mv.getPosition().x;
            item.yPos = mv.getPosition().y;
            item.dx = mv.direction.dx;
            item.dy = mv.direction.dy;
            item.zPosition = mv.getZPosition();
            item.zRotation = mv.getZRotation();
            if(node instanceof ShuttleEnemy) {
                ShuttleEnemy se = (ShuttleEnemy)node;
                item.option1 = se.bitmapName;
                item.classType = ShuttleEnemy.class.getSimpleName();
                item.life = se.lifeBar.getValue();
            } else if(node instanceof Asteroid)
                item.classType = Asteroid.class.getSimpleName();
            else if(node instanceof ShuttlePlayer)
                item.classType = ShuttlePlayer.class.getSimpleName();

            items.add(item);
        }
        return items;
    }





}



















