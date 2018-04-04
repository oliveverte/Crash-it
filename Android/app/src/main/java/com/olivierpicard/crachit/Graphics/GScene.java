package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;

import java.util.List;

/**
 * Scene côté vue, qui va servir à afficher et gérer les éléments de la scene
 */

public abstract class GScene extends GNode implements IGUpdatable, IGDrawable {
    protected int backgroundColor = 0xFF000000;

    public GScene(GSize size) {
        super(size);
    }


    @Override
    abstract public void update(Double currentTime);
    public void touchDown(GPoint pos) { }
    public void touchUp(GPoint pos) { }
    public void touchMove(GPoint pos) { }


    @Override
    public final void draw(Canvas canvas) {
        for(GNode node : children) {
            if(!(node instanceof IGDrawable)) continue;
            ((IGDrawable) node).draw(canvas);
        }
    }


    @Override
    public final void addChild(GNode node) {
        super.addChild(node);
        node.setScene(this);
    }


    @Override
    public final void removeChild(GNode node) {
        children.remove(node);
        node.setScene(this);
    }


    @Override
    public final void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes) {
            this.children.remove(node);
            node.setScene(null);
        }
    }


}
