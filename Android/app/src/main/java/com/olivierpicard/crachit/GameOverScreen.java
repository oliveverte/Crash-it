package com.olivierpicard.crachit;

import android.graphics.Typeface;

import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GPoint;

import java.sql.Time;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Ecran de game over
 * Created by olivierpicard on 06/04/2018.
 */

public class GameOverScreen {
    private final int BUTTON_INTER_SPACE;
    private final GameScene scene;
    private final GLabel scoreText_label;
    private final GLabel score_label;
    private final GLabel saved_label;
    private final GLabel savedScore_label;
    private Button save_button;
    private Button retry_button;
    private Button menu_button;
    private Button saveScore_button;
    private long timer;
    private volatile boolean enable_userInteraction;


    public GameOverScreen(GameScene scene) {
        BUTTON_INTER_SPACE = Tools.screenMetrics.heightPixels / 15;
        this.scene = scene;
        this.enable_userInteraction = true;

        this.score_label = new GLabel(String.valueOf(scene.getScore()));
        this.score_label.setFontSize(48);
        this.score_label.setAlpha(128);
        this.score_label.setFontType(Typeface.create("Helvetica", Typeface.BOLD));
        System.out.println(score_label.getSize());

        this.scoreText_label = new GLabel(Tools.resources.getString(R.string.score));
        this.scoreText_label.setFontSize(64);
        this.scoreText_label.setAlpha(128);
        this.scoreText_label.setFontType(Typeface.create("Helvetica", Typeface.NORMAL));

        this.saved_label = new GLabel(Tools.resources.getString(R.string.partie_a_été_sauvegardé));
        this.saved_label.setFontSize(18);
        this.saved_label.setAlpha(128);
        this.saved_label.setFontType(Typeface.create("Helvetica", Typeface.NORMAL));

        this.savedScore_label= new GLabel(Tools.resources.getString(R.string.score_a_été_sauvegardé));
        this.savedScore_label.setFontSize(18);
        this.savedScore_label.setAlpha(128);
        this.savedScore_label.setFontType(Typeface.create("Helvetica", Typeface.NORMAL));

        this.scoreText_label.setPosition(Tools.fromSceneToScreenPos(new GPoint(0.5f, 0.85f)));
        this.score_label.setPosition(new GPoint(this.scoreText_label.getPosition().x,
                this.scoreText_label.getPosition().y + this.scoreText_label.getSize().height + 5));

        this.save_button = new Button(R.string.sauvegarder_partie);
        this.save_button.setPosition(Tools.fromSceneToScreenPos(new GPoint(0.5f, 0.6f)));

        this.saveScore_button = new Button(R.string.enregister_score);
        this.saveScore_button.setPosition(new GPoint(this.save_button.getPosition().x,
                this.save_button.getPosition().y + this.save_button.getSize().height + BUTTON_INTER_SPACE));

        this.retry_button = new Button(R.string.recommencer);
        this.retry_button.setPosition(new GPoint(this.saveScore_button.getPosition().x,
                this.saveScore_button.getPosition().y + this.saveScore_button.getSize().height + BUTTON_INTER_SPACE));

        this.menu_button= new Button(R.string.accueil);
        this.menu_button.setPosition(new GPoint(this.retry_button.getPosition().x,
                this.retry_button.getPosition().y + this.retry_button.getSize().height + BUTTON_INTER_SPACE));

        this.saved_label.setPosition(this.save_button.getPosition());
        this.savedScore_label.setPosition(this.saveScore_button.getPosition());
    }


    public void hide() {
        this.enable_userInteraction = false;
        this.scene.removeChild(this.scoreText_label);
        this.scene.removeChild(this.score_label);
        this.scene.removeChild(this.save_button);
        this.scene.removeChild(this.retry_button);
        this.scene.removeChild(this.menu_button);
        this.scene.removeChild(this.saved_label);
        this.scene.removeChild(this.saveScore_button);
        this.scene.removeChild(this.savedScore_label);
    }


    public void show() {
        this.score_label.setText(String.valueOf(this.scene.getScore()));
        this.scene.addChild(this.scoreText_label);
        this.scene.addChild(this.score_label);
        this.scene.addChild(this.save_button);
        this.scene.addChild(this.retry_button);
        this.scene.addChild(this.menu_button);
        this.scene.addChild(this.saveScore_button);
        new Timer().schedule(new TimerTask() {
            public void run() { enable_userInteraction = true; }
        }, 0, (long)(0.7*1000));
    }


    public void touchUp(GPoint pos){
        if(!this.enable_userInteraction) return;
        if(save_button.isClicked(pos)) {
            this.scene.removeChild(this.save_button);
            this.scene.addChild(this.saved_label);
//            final encodedDatas = Tools.addEncodedSaveDatas(Tools.KEY_DEFAULT_GAMEINFOS, self.scene.score)
//            UserDefaults.standard.set(encodedDatas, forKey: Tools.KEY_DEFAULT_GAMEINFOS)
        }
        else if(saveScore_button.isClicked(pos)) {
            this.scene.removeChild(this.saveScore_button);
            this.scene.addChild(this.savedScore_label);
//            final encodedDatas = Tools.addEncodedSaveDatas(Tools.KEY_DEFAULT_SCORES, self.scene.score)
//            UserDefaults.standard.set(encodedDatas, forKey: Tools.KEY_DEFAULT_SCORES)
        }
        else if(retry_button.isClicked(pos)) { hide(); scene.start(); }
        else if(menu_button.isClicked(pos)) { hide(); this.scene.switchScreen(GameScene.GameState.welcome); }
    }

}
