package com.olivierpicard.crachit.AnimatedItem;

import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class Asteroid extends AnimatedItem {
    public final int dammage = 50;

    public Asteroid(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public Asteroid(GSize size, int color) {
        super(size, color);
    }
}
