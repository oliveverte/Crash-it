package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class LaserShot extends MovingItem {

    public LaserShot(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public LaserShot(GSize size, int color) {
        super(size, color);
    }
}
