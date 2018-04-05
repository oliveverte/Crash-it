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
    private GScene scene;


    private void init() {
        this.children = new ArrayList<>();
        this.scene = null;
    }


    public GNode() {
        init();
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



    public GScene getScene() { return scene; }
    public void setScene(GScene scene) {
        this.scene = scene;
        for(GNode node : this.children) node.scene = scene;
    }

}
