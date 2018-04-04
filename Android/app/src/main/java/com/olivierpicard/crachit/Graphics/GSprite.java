package com.olivierpicard.crachit.Graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;

import com.olivierpicard.crachit.Tools;

/**
 * Représente une image(sprite) sur la scene
 */

public class GSprite extends GNode implements IGDrawable {
    private int color;
    private Bitmap bitmap;


    private void init() {
        this.color = 0xFFFFFFFF;
        this.bitmap = null;
    }


    public GSprite(@Nullable Integer bitmapRessourceID, int color, GSize size) {
        super(size);
        init();
        this.color = color;
        if(bitmapRessourceID != null)
            this.bitmap = BitmapFactory.decodeResource(Tools.resources, bitmapRessourceID);
    }


    @Override
    public void draw(Canvas canvas) {
        // On défini le rectangle accueillant le dessin
        final Rect bounds = Tools.getRectFromSizeAndPos(this.getPosition(), this.getSize());
        if(this.bitmap == null) {
            // Signifie qu'on doit déssiner un rectangle de couleur
            Paint p = new Paint();
            p.setColor(this.color);
            canvas.drawRect(bounds, p);
        } else
            canvas.drawBitmap(bitmap, null, bounds, null);
    }


    public int getColor() {
        return color;
    }


    public void setColor(int color) {
        this.color = color;
    }


    public Bitmap getBitmap() {
        return bitmap;
    }


    public void setBitmap(int bitmapRessourceID) {
        this.bitmap = BitmapFactory.decodeResource(Tools.resources, bitmapRessourceID);
    }
}
