package com.olivierpicard.crachit;

import android.graphics.Color;

import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GSprite;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ProgressBar extends GSprite {
    public GSprite background;
    public int maxValue;
    private GPoint _nonEditedPosition;
    private int _value;

    @Override
    public void setPosition(GPoint pos) {
        super.setPosition(pos);
        this._nonEditedPosition = pos;
    }


    public int getValue() { return this._value; }

    public void setValue(int value) {
        if(value >= 0) this._value = value;
        else this._value = 0;
        updateView();
    }


    ProgressBar(int maxValue, GSize size) {
        super(Color.GREEN, size);
        this.maxValue = maxValue;
        this._value = maxValue;
        this._nonEditedPosition = GPoint.zero();
        this.background = new GSprite(Color.LTGRAY, size);
        this.background.setZPosition(-1);
        this.addChild(this.background);
    }



    private void updateView() {
        final float percent = this._value * 100 / this.maxValue;
        final float updatedWidth = this.background.getSize().width * percent / 100;
        this.setSize(this.getSize().setWidth(updatedWidth));
        // remet le fond en position initial
        this.background.setPosition(this.background.getPosition().setX(0));
        // remet la barre de progression en position par default
        // On utilise super et pas self, pour modifier la position de la barre
        // sans mettre à jour la position initial (comme la version self.position est surchargé)
        super.setPosition(super.getPosition().setX(this._nonEditedPosition.x));

        // Centre le point pivot (0.5, 0.5) sur le coté gauche du fond
        final float centerOnLeftSide = this.getPosition().x - this.background.getSize().width/2;
        // Aligne le côté gauche de la barre de progression sur le côté gauche du fond
        // A cette étape la barre de progression est bien positionné
        final float alignedTotheLeft = centerOnLeftSide + this.getSize().width/2;

        // A noté que le fond étant un noeud enfant se déplace avec la barre de progression
        // Il faut donc le recentré par rapport à sa position initial

        // Distance entre la position (point pivot: 0.5, 0.5) actuel du fond et la position initial
        final float distanceToCorrectBackgroundPosition = this._nonEditedPosition.x - alignedTotheLeft;
        // On translate le fond de la distance calculé pour quel se soit à sa position initial
        // sans que la barre de progression ne bouge
        final float centeredBackground = this.background.getPosition().x + distanceToCorrectBackgroundPosition;

        // On applique les valeurs calculé
        super.setPosition(super.getPosition().setX(alignedTotheLeft));
        this.background.setPosition(this.background.getPosition().setX(centeredBackground));
    }

}
