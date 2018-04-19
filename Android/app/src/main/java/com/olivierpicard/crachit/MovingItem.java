package com.olivierpicard.crachit;

import android.support.annotation.Nullable;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GSprite;
import com.olivierpicard.crachit.Graphics.GTools;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.Graphics.IGUpdatable;

/**
 * Représente un item(sprite) qui se déplace sur l'écran
 * Created by olivierpicard on 03/04/2018.
 */

public class MovingItem extends GSprite implements IGUpdatable {
    static float base_moving_speed = 1.2f;
    private final int MARGIN_OUT_OF_SCREEN_TO_DELETE = 30;
    public float speed_factor = 1;
    public GVector direction;


    private void init() {
        this.speed_factor = 2.5f;
        this.direction = GVector.down();
    }

    public MovingItem(@Nullable Integer bitmapRessourceID, @Nullable Integer color, GSize size) {
        super(bitmapRessourceID, color, size);
        init();

    }


    public MovingItem(@Nullable Integer bitmapRessourceID, @Nullable Integer color, GSize size,
                      @Nullable Float speed_factor, GVector direction) {
        super(bitmapRessourceID, color, size);
        init();
        if(bitmapRessourceID != null) this.setBitmap(bitmapRessourceID);
        if(speed_factor != null) this.speed_factor = speed_factor;
        this.direction = direction;
    }



    public void update(long currentTime) {
        final float realSpeed = MovingItem.base_moving_speed * this.speed_factor;
        final GVector realDirection = new GVector(this.direction).multiply(realSpeed);
        setPosition(this.getPosition().add(realDirection));
    }


    public boolean isOutOfScreen() {

        // Anchor point 0.5 par défaut donc on divise la taille par 2
        if (getPosition().y + getSize().height / 2 < -MARGIN_OUT_OF_SCREEN_TO_DELETE
                || getPosition().y - getSize().height / 2 > this.getScene().getSize().height + MARGIN_OUT_OF_SCREEN_TO_DELETE
                || getPosition().x - getSize().width / 2 > this.getScene().getSize().width + MARGIN_OUT_OF_SCREEN_TO_DELETE
                || getPosition().x + getSize().width / 2 < -MARGIN_OUT_OF_SCREEN_TO_DELETE) {
            return true;
        }
        return false;
    }


    public boolean isOverlaps(ICollisionable item) {
        if(getPosition().x - getSize().width/2 < item.getPosition().x + item.getSize().width/2
                && getPosition().x + getSize().width/2 > item.getPosition().x - item.getSize().width/2
                && getPosition().y + getSize().height/2 > item.getPosition().y - item.getSize().height/2
                && getPosition().y - getSize().height/2 < item.getPosition().y + item.getSize().height/2) {
            return true;
        }
        return false;
    }
}
