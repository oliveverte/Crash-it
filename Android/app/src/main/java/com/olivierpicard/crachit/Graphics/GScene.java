package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;

import java.util.Collections;
import java.util.List;
import java.util.ListIterator;

/**
 * Scene côté vue, qui va servir à afficher et gérer les items de la scene
 */

public abstract class GScene extends GNode {
    protected int backgroundColor = 0xFF000000;

    abstract public void didInitialized();
    abstract public void update(Double currentTime);

    public void touchDown(GPoint pos) { }
    public void touchUp(GPoint pos) { }
    public void touchMove(GPoint pos) { }


    public final void render(Canvas canvas) {
        try {
            List syncChildren = Collections.synchronizedList(children);
            synchronized (syncChildren) {
                ListIterator iterator = syncChildren.listIterator();
                Object node;
                while(iterator.hasNext()) {
                    node = iterator.next();
                    if (!(node instanceof IGDrawable)) continue;
                    ((IGDrawable) node).render(canvas);
                }
            }
        } catch (Exception e) {}

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
