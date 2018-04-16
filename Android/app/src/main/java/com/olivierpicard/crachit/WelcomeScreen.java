package com.olivierpicard.crachit;

import android.graphics.Color;
import android.graphics.Typeface;

import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class WelcomeScreen {
    private final GameScene scene;
    private final GLabel title_label;
    private final Button play_button;
    private final Button resume_button;
    private final Button score_button;

    public WelcomeScreen(GameScene scene) {
        this.scene = scene;
        this.title_label = new GLabel("Crash it");
        this.title_label.setAlpha(128);
        this.title_label.setFontType(Typeface.create("Helvetica", Typeface.NORMAL));
        this.title_label.setFontSize(55);
        this.title_label.setColor(GTools.setColorOpacity(Color.WHITE, 150));

        this.play_button = new Button(R.string.jouer);
        this.resume_button = new Button(R.string.reprendre);
        this.score_button = new Button(R.string.scores);

        this.title_label.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.5f, 0.8f)));

        this.play_button.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.5f, 0.5f)));

        this.resume_button.setPosition(new GPoint(this.play_button.getPosition().x,
                this.play_button.getPosition().y + this.play_button.getSize().height + 30));

        this.score_button.setPosition(new GPoint( this.resume_button.getPosition().x,
                this.resume_button.getPosition().y + this.resume_button.getSize().height + 30));
    }


    public void show() {
        this.scene.addChild(this.title_label);
        this.scene.addChild(this.play_button);
        this.scene.addChild(this.resume_button);
        this.scene.addChild(this.score_button);
    }

    public void hide() {
        this.scene.removeChild(this.title_label);
        this.scene.removeChild(this.play_button);
        this.scene.removeChild(this.resume_button);
        this.scene.removeChild(this.score_button);

    }

    public void touchUp(GPoint pos) {
        System.out.println(pos);
        if(this.play_button.isClicked(pos)) { hide(); this.scene.start(); }
        else if(this.resume_button.isClicked(pos)) { /* Change to Resume View*/ }
        else if(this.score_button.isClicked(pos)) { /* Change to Resume Scores*/ }
    }

}
