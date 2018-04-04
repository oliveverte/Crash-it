package com.olivierpicard.crachit.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un un objet ayant une position, une taille, une angle de rotation,
 * qui peut contenir des noeuds enfant,
 */

public class GNode {
    protected List<GNode> children;
    private GPoint position;
    private GSize size;
    private GScene scene;
    private float zRotation;


    private void init() {
        this.children = new ArrayList<>();
        this.position = GPoint.zero();
        this.size = GSize.zero();
        this.scene = null;
    }


    public GNode(GSize size) {
        init();
        this.size = size;
    }


    public void addChild(GNode node) {
        children.add(node);
        node.scene = this.scene;
    }


    public void removeChild(GNode node) {
        children.remove(node);
    }


    public void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes)  this.children.remove(node);
    }


    public GPoint getPosition() { return position; }
    public void setPosition(GPoint position) { this.position = position;}

    public GSize getSize() { return size; }
    public void setSize(GSize size) { this.size = size; }

    public GScene getScene() { return scene; }

    public void setScene(GScene scene) {
        this.scene = scene;
        for(GNode node : this.children) node.scene = scene;
    }

    public float getZRotation() { return zRotation; }
    public void setZRotation(float zRotation) { this.zRotation = zRotation; }
}
