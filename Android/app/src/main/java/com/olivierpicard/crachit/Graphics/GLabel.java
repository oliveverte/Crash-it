package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;

import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GRelativeRender;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.IGDrawable;
import com.olivierpicard.crachit.Tools;


/**
 * Created by olivierpicard on 14/04/2018.
 */

public class GLabel extends GNode implements IGDrawable {
    /*
     * Défini un alignement pour masquer à l'utilisateur celui de Paint.Align
     * (Plus conviviale et plus indépendant)
     */
    enum TextAlign {
        LEFT,
        CENTER,
        RIGHT
    }
    private String text;
    private int fontSize;
    private Typeface fontType;
    private Paint.Align textAlign;

    private GSize size;
    private GPoint position;
    private float zRotation;
    private int color;
    private GRelativeRender relativeRender;

    private void init() {
        this.size = GSize.zero();
        this.position = GPoint.zero();
        this.zRotation = 0;
        this.color = Color.WHITE;
        this.relativeRender = new GRelativeRender();
        this.textAlign = Paint.Align.CENTER;

        this.text = "";
        this.fontType = Typeface.create("Helvetica", Typeface.NORMAL);
        this.fontSize = 20;
        this.relativeRender = new GRelativeRender();
    }


    public GLabel() {
        super();
        init();
    }

    public GLabel(String text) {
        super();
        init();
        this.text = text;
    }

    public GLabel(String text, int fontSize) {
        super();
        init();
        this.text = text;
        this.fontSize = fontSize;
    }

    public GLabel(String text, int fontSize, Typeface fontType) {
        super();
        init();
        this.text = text;
        this.fontSize = fontSize;
        this.fontType = fontType;
    }

    public GLabel(String text, Typeface fontType) {
        super();
        init();
        this.text = text;
        this.fontType = fontType;
    }

    @Override
    public void render(Canvas canvas) {
        if(isHidden()) return;
        canvas.save();
        this.relativeRender.processChildRelativity(this);
        final Rect bounds = Tools.getRectFromSizeAndPos(this.relativeRender.position, this.getSize());
        canvas.rotate(this.relativeRender.zRotation, this.relativeRender.position.x, this.relativeRender.position.y);

        Paint p = new Paint();
        p.setTextSize(this.fontSize);
        p.setColor(this.color);
        p.setAntiAlias(true);
        p.setTextAlign(this.textAlign);

        canvas.drawText(this.text, bounds.exactCenterX(), bounds.exactCenterY(), p);

        canvas.restore();
    }



    public int getColor() {
        return color;
    }
    public void setColor(int color) {
        this.color = color;
    }

    public GSize getSize() {
        Paint p = new Paint();
        p.setTextSize(this.fontSize);
        Rect bounds = new Rect();
        p.getTextBounds(this.text, 0, this.text.length(), bounds);
        return new GSize(bounds.width(), bounds.height());
    }

    public void setSize(GSize size) { this.size = size; }

    public float getZRotation() { return zRotation; }
    public void setZRotation(float zRotation) { this.zRotation = zRotation; }

    public GPoint getPosition() { return position; }
    public void setPosition(GPoint position) { this.position = position;}

    public String getText() {
        return text;
    }
    public void setText(String text) {
        this.text = text;
    }

    public int getFontSize() {
        return fontSize;
    }
    public void setFontSize(int fontSize) {
        this.fontSize = fontSize;
    }

    public Typeface getFontType() {
        return fontType;
    }
    public void setFontType(Typeface fontType) {
        this.fontType = fontType;
    }

    public int getAlpha() {return (this.color >> 24) & 0xff;}
    public void setAlpha(int value) {
        this.color = Tools.setColorOpacity(this.color, value);
    }

    public GRelativeRender getRelativeRender() {
        return relativeRender;
    }


    public TextAlign getTextAlign() {
        TextAlign align = TextAlign.CENTER;
        switch (this.textAlign) {
            case LEFT:
                align = TextAlign.LEFT;
                break;
            case CENTER:
                align = TextAlign.CENTER;
                break;
            case RIGHT:
                align = TextAlign.RIGHT;
                break;
        }
        return align;
    }


    public void setTextAlign(TextAlign textAlign) {
        switch (textAlign) {
            case LEFT:
                this.textAlign = Paint.Align.LEFT;
                break;
            case CENTER:
                this.textAlign = Paint.Align.CENTER;
                break;
            case RIGHT:
                this.textAlign = Paint.Align.RIGHT;
                break;
        }
    }



}
