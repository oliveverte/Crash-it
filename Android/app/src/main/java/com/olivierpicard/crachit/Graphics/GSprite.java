package com.olivierpicard.crachit.Graphics;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;

import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.ProgressBar;
import com.olivierpicard.crachit.Shuttle.ShuttlePlayer;

/**
 * Représente une image(sprite) sur la scene
 */

public class GSprite extends GNode implements IGDrawable {
    private GSize size;
    private GPoint position;
    private float zRotation;
    private int color;
    private Bitmap bitmap;
    private GRelativeRender relativeRender;
    public GRelativeRender.RotationPivot rotationPivot;


    private void init() {
        this.size = GSize.zero();
        this.position = GPoint.zero();
        this.zRotation = 0;
        this.color = Color.GRAY;
        this.bitmap = null;
        this.relativeRender = new GRelativeRender();
        rotationPivot = GRelativeRender.RotationPivot.ROOT;
    }


    public GSprite(@Nullable Integer bitmapRessourceID,
                   @Nullable Integer color, GSize size) {
        super();
        init();
        if(bitmapRessourceID != null)
            this.bitmap = BitmapFactory.decodeResource(GTools.resources, bitmapRessourceID);
        if(color != null)
            this.color = color;
        this.size = size;
    }


    @Override
    public void render(Canvas canvas) {
        if(isHidden()) return;
        canvas.save();
        // On défini le rectangle accueillant le dessin
        this.relativeRender.processChildRelativity(this);
        final Rect bounds = GTools.getRectFromSizeAndPos(this.relativeRender.position, this.getSize());

        GPoint pivotPosition = this.relativeRender.position;

        if(rotationPivot == GRelativeRender.RotationPivot.ROOT) {
            GNode rootPosition = this.getRootParent();
            if(rootPosition instanceof IGDrawable)
                pivotPosition = ((IGDrawable) rootPosition).getPosition();
        }

        canvas.rotate(this.relativeRender.zRotation, pivotPosition.x, pivotPosition.y);

        if(this.bitmap == null) {
            // Signifie qu'on doit déssiner un rectangle de couleur
            Paint p = new Paint();
            p.setColor(this.color);
            canvas.drawRect(bounds, p);
        } else {
            Paint p = new Paint();
            p.setAlpha(this.getAlpha());
            canvas.drawBitmap(bitmap, null, bounds, p);
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
        this.bitmap = BitmapFactory.decodeResource(GTools.resources, bitmapRessourceID);
    }
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }


    public GSize getSize() { return size; }
    public void setSize(GSize size) { this.size = size; }

    public float getZRotation() { return zRotation; }
    public void setZRotation(float zRotation) { this.zRotation = zRotation; }

    public GPoint getPosition() { return position; }
    public void setPosition(GPoint position) { this.position = position;}

    public int getAlpha() {return (this.color >> 24) & 0xff;}
    public void setAlpha(int value) {
        this.color = GTools.setColorOpacity(this.color, value);
    }

    public GRelativeRender getRelativeRender() {
        return relativeRender;
    }
}
