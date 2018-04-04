package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;

/**
 * Dessine l'objet à l'écran, en prenant en compte
 * les modifications fait lors de l'update
 */

public interface IGDrawable {
    void draw(Canvas canvas);
}
