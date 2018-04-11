package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.PorterDuff;

import java.util.ArrayList;
import java.util.List;

/**
 * Scene côté vue, qui va servir à afficher
 * et gérer les items de la scene
 */

public abstract class GScene extends GNode implements Runnable {
    protected int backgroundColor = Color.BLACK;
    private List<GNode> elementsToAdd;
    private List<GNode> elementsToRemove;

    public GScene() {
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
    }

    abstract public void didInitialized();
    abstract public void update(long currentTime);

    public void touchDown(GPoint pos) { }
    public void touchUp(GPoint pos) { }
    public void touchMove(GPoint pos) { }


    private final void render(Canvas canvas) {
        for(GNode node : this.children) {
            if (!(node instanceof IGDrawable)) continue;
            ((IGDrawable) node).render(canvas);
        }
    }


    private final void refreshSceneNodes() {
        this.children.removeAll(elementsToRemove);
        this.children.addAll(elementsToAdd);

        // On peut utilisé clear, mais il me semble
        // que le garbageCollector fera mieux son travail
        // que clear()
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
    }




    public void run() {
        didInitialized();
        while(true) {
            update(System.currentTimeMillis());
            refreshSceneNodes();
            Canvas canvas = GSceneViewController.surfaceHolder.lockCanvas();
            if (canvas != null) {
                synchronized (GSceneViewController.surfaceHolder) {
                    canvas.drawColor(backgroundColor, PorterDuff.Mode.CLEAR);
                    render(canvas);
                }
                GSceneViewController.surfaceHolder.unlockCanvasAndPost(canvas);
            }

            try {
                Thread.sleep(16);
            } catch (Exception e) {}
            
        }
    }


    /**
     * Ajoute un noeud ansi que ses enfants
     * de la scène lors de la prochaine frame
     * @param node noeud à ajouter
     */
    @Override
    public final void addChild(GNode node) {
        node.setScene(this);
        this.elementsToAdd.add(node);
        for(GNode child : node.children) {
            child.setScene(this);
            this.elementsToAdd.add(child);
        }
    }

    /**
     * Supprime un noeud ansi que ses enfants
     * de la scène lors de la prochaine frame
     * @param node noeud à supprimer
     */
    @Override
    public final void removeChild(GNode node) {
        node.setScene(null);
        this.elementsToRemove.add(node);
        // Supprime l'élément ainsi que tous ses enfants
        for(GNode child : node.children) {
            child.setScene(null);
            this.elementsToRemove.add(child);
        }
    }

    /**
     * Supprime une liste de noeuds ansi que tous leurs enfants
     * de la scène lors de la prochaine frame
     * @param nodes liste de noeuds à supprimer
     */
    @Override
    public final void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes) {
            node.setScene(null);
            this.elementsToRemove.add(node);
            // Supprime l'élément ainsi que tous ses enfants
            for(GNode child : node.children) {
                child.setScene(null);
                this.elementsToRemove.add(child);
            }
        }
    }
}
