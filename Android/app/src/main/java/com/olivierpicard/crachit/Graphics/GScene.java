package com.olivierpicard.crachit.Graphics;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.Rect;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Scene côté vue, qui va servir à afficher
 * et gérer les items de la scene
 */
public abstract class GScene extends GNode implements Runnable {
    protected int backgroundColor = Color.BLACK;
    private List<GNode> elementsToAdd;
    private List<GNode> elementsToRemove;
    private GSize baseSize;
    private GSize scale;

    public volatile boolean enable = false;
    private GSize size;
    private long time_from_lastFrame = 0;


    abstract public void didInitialized();
    abstract public void update(long currentTime);

    public void touchDown(GPoint pos) { }
    public void touchUp(GPoint pos) { }
    public void touchMove(GPoint pos) { }


    public void init(GSize baseSceneSize) {
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
        this.baseSize = baseSceneSize;
        final float xScale = GTools.screenMetrics.widthPixels / this.baseSize.width;
        final float yScale = GTools.screenMetrics.heightPixels / this.baseSize.height;
        this.scale = new GSize(xScale, yScale);
        this.size = new GSize(GTools.screenMetrics.widthPixels / this.scale.width,
                GTools.screenMetrics.heightPixels/ this.scale.height);
    }


    public void run() {
        didInitialized();
        while(this.enable) {
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



    private void render(Canvas canvas) {
        canvas.scale(this.scale.width, this.scale.height, GTools.screenMetrics.widthPixels,
                GTools.screenMetrics.heightPixels);
        canvas.translate(GTools.screenMetrics.widthPixels - this.size.width,
                GTools.screenMetrics.heightPixels - this.size.height);

        Map<Integer, List<GNode>> renderElements = new LinkedHashMap<>();
        processRenderOrder(renderElements);
        SortedSet<Integer> orderedRenderElement = new TreeSet<>(renderElements.keySet());
        Paint p = new Paint();
        p.setColor(Color.RED);
        canvas.drawRect(new Rect(0, 0, 40, 40), p);
        p.setColor(Color.BLUE);
        canvas.drawRect(new Rect((int)this.size.width-40, (int)this.size.height-40, (int)this.size.width, (int)this.size.height), p);
        for(Integer id : orderedRenderElement) {
            for(GNode node : renderElements.get(id)) {
                if (!(node instanceof IGDrawable)) continue;
                ((IGDrawable) node).render(canvas);
            }
        }

        p.setTextSize(12);
        p.setColor(Color.WHITE);
        p.setAntiAlias(true);
        p.setTextAlign(Paint.Align.LEFT);

        canvas.drawText("FPS : " + (System.currentTimeMillis() - this.time_from_lastFrame),
                0, (int)this.size.height - 20, p);
        canvas.drawText("Nodes : " + children.size(),
                0, (int)this.size.height - 5, p);
        this.time_from_lastFrame = System.currentTimeMillis();
    }


    private void refreshSceneNodes() {
        for(GNode node : elementsToRemove) {
            this.children.remove(node);
            node.setScene(null);
        }
        this.children.addAll(elementsToAdd);

        // On peut utilisé clear, mais il me semble
        // que le garbageCollector fera mieux son travail
        // que clear()
        elementsToAdd = new ArrayList<>();
        elementsToRemove = new ArrayList<>();
    }

    /**
     * Organise tous les noeuds de la scène selon leur position en z
     * @param map : Key: position en Z  -  Value: Tous les élements à cette position
     */
    private void processRenderOrder(Map<Integer, List<GNode>> map) {
        for(GNode node : this.children) {
            List<GNode> list = map.get(node.getZPosition());
            if(list == null) list = new ArrayList<>();
            list.add(node);
            map.put(node.getZPosition(), list);
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
        this.elementsToRemove.add(node);
        // Supprime l'élément ainsi que tous ses enfants
        for(GNode child : node.children)
            this.elementsToRemove.add(child);
    }

    /**
     * Supprime une liste de noeuds ansi que tous leurs enfants
     * de la scène lors de la prochaine frame
     * @param nodes liste de noeuds à supprimer
     */
    @Override
    public final void removeChildren(List<GNode> nodes) {
        for (GNode node: nodes) {
            this.elementsToRemove.add(node);
            // Supprime l'élément ainsi que tous ses enfants
            for(GNode child : node.children)
                this.elementsToRemove.add(child);
        }
    }

    public GSize getSize() {
        return size;
    }
}
