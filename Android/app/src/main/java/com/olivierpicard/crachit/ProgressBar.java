package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GSprite;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ProgressBar extends GSprite {

    public ProgressBar(int bitmapRessourceID, GSize size) {
        super(bitmapRessourceID, size);
    }

    public ProgressBar(GSize size, int color) {
        super(size, color);
    }
}
