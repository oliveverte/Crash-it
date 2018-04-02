package com.olivierpicard.crachit.Graphics;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GPoint {
    public float x, y;

    private GPoint() {}

    public GPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(GPoint p) {
        this.x += p.x;
        this.y += p.y;
    }

    public void multiply(GPoint p) {
        this.x *= p.x;
        this.y *= p.y;
    }

    @Override
    public String toString() {
        return "(x: " + this.x + ", y: " + this.y + ")";
    }

    public static GPoint zero() {
        return new GPoint(0f, 0f);
    }
}
