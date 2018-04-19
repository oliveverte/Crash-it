package com.olivierpicard.crachit.Graphics;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GSize {
    public float height, width;

    public GSize(float w, float h) {
        this.width = w;
        this.height = h;
    }

    public GSize(GSize size) {
        this.width = size.width;
        this.height = size.height;
    }

    public GSize setWidth(float w) {
        this.width = w;
        return this;
    }

    public GSize setHeight(float h) {
        this.height = h;
        return this;
    }

    @Override
    public String toString() {
        return "(w: " + this.width + ", h: " + this.height + ")";
    }

    public static GSize zero() { return new GSize(0, 0); }
}
