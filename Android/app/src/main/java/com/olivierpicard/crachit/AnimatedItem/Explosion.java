package com.olivierpicard.crachit.AnimatedItem;

import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class Explosion extends AnimatedItem {
    public Explosion(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public Explosion(GSize size, int color) {
        super(size, color);
    }
}
