package com.olivierpicard.crachit.Graphics;

/**
 * Représente un point sur la scene équivalent à position
 */

public class GPoint {
    public float x, y;

    public GPoint(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void add(GPoint p) {
        this.x += p.x;
        this.y += p.y;
    }

    public GPoint add(GVector v) {
        return new GPoint(this.x + v.dx, this.y + v.dy);
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
        return new GPoint(0, 0);
    }
}
