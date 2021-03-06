package com.olivierpicard.crachit.AnimatedItem;

import com.olivierpicard.crachit.Graphics.GTools;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Une explosion annimé
 * Created by olivierpicard on 06/04/2018.
 */

public class Explosion extends AnimatedItem {

    public Explosion(GPoint position, GSize size){
        super(GTools.getListOfDrawableRessouce("explosion_", 1, 16),
                1, size, (long)(0.1*1000), true);
        this.setPosition(position);
        this.setZPosition(500);
    }

    public Explosion(GPoint position) {
        super(GTools.getListOfDrawableRessouce("explosion_", 1, 16),
                1, new GSize(70, 70), (long)(0.1*1000), true);
        this.setPosition(position);
        this.setZPosition(500);
    }

}
