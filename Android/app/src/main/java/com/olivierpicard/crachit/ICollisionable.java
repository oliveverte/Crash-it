package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 04/04/2018.
 */

public interface ICollisionable {
    boolean isCollisionEnabled();
    void setCollisionEnable(boolean value);

    GSize getSize();
    void setSize(GSize size);

    GPoint getPosition();
    void setPosition(GPoint point);

    void inCollisionWith(ICollisionable item);
    boolean isOverlaps(ICollisionable item);
}
