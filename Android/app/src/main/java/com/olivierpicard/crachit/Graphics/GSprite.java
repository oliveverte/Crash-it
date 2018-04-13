package com.olivierpicard.crachit.Graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;

import com.olivierpicard.crachit.Shuttle.ShuttleEnemy;
import com.olivierpicard.crachit.Tools;

/**
 * Représente une image(sprite) sur la scene
 */

public class GSprite extends GNode implements IGDrawable {
    private GSize size;
    private GPoint position;
    private float zRotation;
    private int color;
    private Bitmap bitmap;
    private RelativeRender relativeRender;


    private void init() {
        this.size = GSize.zero();
        this.position = GPoint.zero();
        this.zRotation = 0;
        this.color = 0xFFFFFFFF;
        this.bitmap = null;
        this.relativeRender = new RelativeRender();
    }


    public GSprite(int bitmapRessourceID, GSize size) {
        super();
        init();
        this.bitmap = BitmapFactory.decodeResource(Tools.resources, bitmapRessourceID);
        this.size = size;
    }


    public GSprite(GSize size, int color) {
        super();
        init();
        this.color = color;
        this.size = size;
    }

    public GSprite(@Nullable Integer bitmapRessourceID, int color, GSize size) {
        super();
        init();
        if(bitmapRessourceID != null)
            this.bitmap = BitmapFactory.decodeResource(Tools.resources, bitmapRessourceID);
        this.color = color;
        this.size = size;
    }



    @Override
    public void render(Canvas canvas) {
        canvas.save();
        // On défini le rectangle accueillant le dessin

        this.relativeRender.processChildRelativity(this);
        final Rect bounds = Tools.getRectFromSizeAndPos(this.relativeRender.position, this.getSize());
//        if(this instanceof ShuttleEnemy) {
//            Paint p = new Paint();
//            p.setColor(this.color);
//            canvas.drawRect(bounds, p);
//        }
        canvas.rotate(this.relativeRender.zRotation, this.relativeRender.position.x, this.relativeRender.position.y);
        if(this.bitmap == null) {
            // Signifie qu'on doit déssiner un rectangle de couleur
            Paint p = new Paint();
            p.setColor(this.color);
            canvas.drawRect(bounds, p);
        } else {
            canvas.drawBitmap(bitmap, null, bounds, null);
        }
        canvas.restore();
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

    public GSize getSize() { return size; }
    public void setSize(GSize size) { this.size = size; }

    public float getZRotation() { return zRotation; }
    public void setZRotation(float zRotation) { this.zRotation = zRotation; }

    public GPoint getPosition() { return position; }
    public void setPosition(GPoint position) { this.position = position;}

    public RelativeRender getRelativeRender() {
        return relativeRender;
    }
}
