package com.olivierpicard.crachit.Graphics;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GVector {
    public float dx, dy;

    public GVector(float dx, float dy) {
        this.dx = dx;
        this.dy = dy;
    }

    public GVector(GVector vector) {
        this.dx = vector.dx;
        this.dy = vector.dy;
    }

    public GVector add(GVector v) {
        return new GVector(this.dx + v.dx, this.dy + v.dy);
    }

    public GVector multiply(float factor) {
        return new GVector(this.dx * factor, this.dy * factor);
    }

    @Override
    public String toString() {
        return "(dx: " + this.dx + ", dy: " + this.dy + ")";
    }
    
    public static GVector zero() { return new GVector(0, 0); }
    public static GVector up() { return new GVector(0, 1); }
    public static GVector down() { return new GVector(0, -1); }
    public static GVector left() { return new GVector(-1, 0); }
    public static GVector right() { return new GVector(1, 0); }
}
