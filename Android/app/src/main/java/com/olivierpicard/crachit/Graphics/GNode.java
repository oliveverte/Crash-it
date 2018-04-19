package com.olivierpicard.crachit.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un un objet qui peut contenir des noeuds enfant,
 */

public abstract class GNode{
    protected List<GNode> children;
    protected GNode parent;
    private boolean hidden;


    /** 0 est la position la plus éloignée */
    private int zPosition;
    private GScene scene;

    private void init() {
        this.children = new ArrayList<>();
        this.scene = null;
        this.parent = null;
        this.zPosition = 0;
    }


    public GNode() {
        init();
    }


    /**
     * Ajoute un noeud enfant à celui-ci
     * et l'ajoute à la même scène que ce noeud
     * dans le cas où scène n'est pas null
     * @param node noeud à ajouter
     */
    public void addChild(GNode node) {
        node.parent = this;
        children.add(node);
        if(this.scene != null) node.setScene(this.scene);
    }

    /**
     * Supprime un noeud enfant à celui-ci
     * et supprime de la même scène que ce noeud
     * dans le cas où scène n'est pas null
     * @param node noeud à supprimer
     */
    public void removeChild(GNode node) {
        node.parent = null;
        this.children.remove(node);
        if(this.scene != null)
            this.scene.removeChild(node);
    }


    /**
     * Supprime une liste de noeuds enfant à celui-ci
     * et les supprime de la même scène que ce noeud
     * dans le cas où scène n'est pas null
     * @param nodes noeud à supprimer
     */
    public void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes) {
            node.parent = null;
            this.children.remove(node);
            node.setScene(null);
            if(this.scene != null)
                this.scene.removeChild(node);
        }
    }




    public GScene getScene() { return this.scene; }
    public void setScene(GScene scene) {
        for(GNode node : this.children) node.setScene(scene);
        this.scene = scene;
        if(this.scene != null) this.scene.addChild(this);
    }

    public int getZPosition() { return zPosition; }
    public void setZPosition(int zPosition) { this.zPosition = zPosition; }


    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public List<GNode> getDeepChildren() {
        final List<GNode> children = new ArrayList<>(this.children);
        for(GNode node : this.children) children.addAll(node.getDeepChildren());
        return children;
    }
}


























