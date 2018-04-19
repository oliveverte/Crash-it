package com.olivierpicard.crachit;

import android.graphics.Color;
import android.graphics.Typeface;

import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GSprite;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Created by olivierpicard on 19/04/2018.
 */

public class TutoImage {
    GSprite click_left;
    GSprite click_right;
    GSprite middle_line;
    GLabel explain_text;
    GLabel explain_text2;
    GScene scene;

    TutoImage(GScene scene) {
        this.scene = scene;
        this.middle_line = new GSprite(null, Color.WHITE, new GSize(2, this.scene.getSize().height));
        this.middle_line.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.5f, 0.5f)));

        this.click_left = new GSprite(R.drawable.pointer, null, new GSize(60, 60));
        this.click_left.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.25f, 0.3f)));


        this.click_right = new GSprite(R.drawable.pointer, null, new GSize(70, 70));
        this.click_right.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.75f, 0.4f)));

        this.explain_text = new GLabel(GTools.resources.getString(R.string.guide_phrase),15, Typeface.create("Helvetica", Typeface.NORMAL));
        this.explain_text.setTextAlign(GLabel.TextAlign.CENTER);
        this.explain_text.setColor(Color.WHITE);
        this.explain_text.setAlpha(178);
        this.explain_text.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.5f, 0.6f)));

        this.explain_text2 = new GLabel(GTools.resources.getString(R.string.guide_phrase2),15, Typeface.create("Helvetica", Typeface.NORMAL));
        this.explain_text2.setTextAlign(GLabel.TextAlign.CENTER);
        this.explain_text2.setColor(Color.WHITE);
        this.explain_text2.setAlpha(178);
        this.explain_text2.setPosition(GTools.fromSceneToScreenPos(this.scene.getSize(), new GPoint(0.5f, 0.65f)));

        this.click_left.setAlpha(125);
        this.click_right.setAlpha(125);
        this.middle_line.setAlpha(89);

        this.scene.addChild(this.click_left);
        this.scene.addChild(this.click_right);
        this.scene.addChild(this.middle_line);
        this.scene.addChild(this.explain_text);
        this.scene.addChild(this.explain_text2);
    }

    public void delete() {
        this.scene.removeChild(this.click_left);
        this.scene.removeChild(this.click_right);
        this.scene.removeChild(this.middle_line);
        this.scene.removeChild(this.explain_text);
        this.scene.removeChild(this.explain_text2);
    }
}
