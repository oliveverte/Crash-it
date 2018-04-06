package com.olivierpicard.crachit.Shuttle;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.MovingItem;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class Shuttle extends MovingItem {
    public Shuttle(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public Shuttle(GSize size, int color) {
        super(size, color);
    }
}
