package com.olivierpicard.crachit.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GNode {
    private List<GNode> children;
    private GPoint position;
    private GSize size;

    private void init() {
        this.children = new ArrayList<>();
        this.position = GPoint.zero();
        this.size = GSize.zero();
    }

    public GNode() {
        init();
    }

    public GNode(GSize size) {
        init();
        this.size = size;
    }

    public void addChild(GNode node) {
        children.add(node);
    }

    public void removeChild(GNode node) {
        children.remove(node);
    }

    public void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes)  this.children.remove(node);
    }

}
