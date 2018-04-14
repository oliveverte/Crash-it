package com.olivierpicard.crachit;

import android.graphics.Color;
import android.graphics.Typeface;

import com.olivierpicard.crachit.Graphics.GLabel;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GSprite;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class Button extends GSprite {
    private GLabel label;


    private void init() {
        this.label = new GLabel("");
        this.label.setFontSize(20);
        this.label.setPosition(new GPoint(this.getPosition().x, this.getPosition().y + this.getSize().height/6));
        this.label.setFontType(Typeface.create("Helvetica", Typeface.NORMAL));
        this.label.setColor(Color.BLACK);
        this.label.setZPosition(1100);
        this.setAlpha(140);
        addChild(this.label);
        this.setZPosition(1000);
    }


    public Button(String text, GSize size) {
        super(null, Color.WHITE, size);
        init();
        this.label.setText(text);
    }


    public Button(int ressourceTextID) {
        super(null, Color.WHITE, new GSize(200, 40));
        init();
        this.label.setText(GTools.resources.getString(ressourceTextID));
    }


    public boolean isClicked(GPoint touchAt) {
        if(this.getScene() != null
                && touchAt.x > this.getPosition().x - this.getSize().width/2
                && touchAt.x < this.getPosition().x + this.getSize().width/2
                && touchAt.y < this.getPosition().y + this.getSize().height/2
                && touchAt.y > this.getPosition().y - this.getSize().height/2) {
            return true;
        }
        return false;
    }


    public String getText() {
        return label.getText();
    }


    public void setText(String text) {
        this.label.setText(text);
    }
}