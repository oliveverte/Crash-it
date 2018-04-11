package com.olivierpicard.crachit.Graphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un un objet qui peut contenir des noeuds enfant,
 */

public abstract class GNode {
    protected List<GNode> children;
    protected GNode parent;


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
        node.scene = this.scene;
        // Si le parent est déjà dans une scène
        if(this.scene != null) {
            // On ajoute le nouveau noeud à la scène
            this.scene.addChild(node);
        }
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
        // Si le parent (this) est contenu dans une scène
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
            // Si le parent (this) est contenu dans une scène
            if(this.scene != null)
                this.scene.removeChild(node);
        }
    }



    public GScene getScene() { return scene; }

    public void setScene(GScene scene) {
        this.scene = scene;
        for(GNode node : this.children) node.scene = scene;
    }

    public int getZPosition() { return zPosition; }
    public void setZPosition(int zPosition) { this.zPosition = zPosition; }


}
