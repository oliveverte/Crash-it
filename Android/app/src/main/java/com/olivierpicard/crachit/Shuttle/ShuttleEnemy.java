package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ShuttleEnemy extends Shuttle {
    public ShuttleEnemy(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public ShuttleEnemy(GSize size, int color) {
        super(size, color);
    }
}
