package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ShuttlePlayer extends Shuttle {
    public ShuttlePlayer(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public ShuttlePlayer(GSize size, int color) {
        super(size, color);
    }
}
