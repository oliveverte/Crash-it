package com.olivierpicard.crachit.AnimatedItem;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.MovingItem;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class AnimatedItem extends MovingItem {
    public AnimatedItem(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public AnimatedItem(GSize size, int color) {
        super(size, color);
    }
}
