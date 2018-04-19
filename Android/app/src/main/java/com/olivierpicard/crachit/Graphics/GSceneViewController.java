package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.olivierpicard.crachit.Scores;

import static android.support.v4.content.ContextCompat.startActivity;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GSceneViewController extends SurfaceView implements SurfaceHolder.Callback {
    private Class sceneType;
    private GScene scene;
    public static SurfaceHolder surfaceHolder;
    public final GSize resolution = new GSize(360, 600);
    private Thread sceneThread;

    public GSceneViewController(Context context) {
        super(context);
    }


    public GSceneViewController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }



    public void confScene(Class sceneType){
        this.sceneType = sceneType;
    }

    public void destroyCurrentScene() {
        if(this.scene == null) return;
        this.scene.enable = false;
    }



    public void onTouch(MotionEvent ev) {
        final GPoint pos = new GPoint(ev.getX() - (GTools.screenMetrics.widthPixels - this.scene.getSize().width),
                ev.getY() - (GTools.screenMetrics.heightPixels - this.scene.getSize().height));
        final GPoint percent = new GPoint((ev.getX()*100)/GTools.screenMetrics.widthPixels,
                (ev.getY()*100)/GTools.screenMetrics.heightPixels);
        final GPoint realPos = new GPoint((this.scene.getSize().width*percent.x)/100,
                (this.scene.getSize().height * percent.y)/100);
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                this.scene.touchEvent.edit(GScene.TouchType.DOWN, realPos);
                break;
            case MotionEvent.ACTION_UP :
                this.scene.touchEvent.edit(GScene.TouchType.UP, realPos);
                break;
            case MotionEvent.ACTION_MOVE :
                this.scene.touchEvent.edit(GScene.TouchType.MOVE, realPos);
                break;
        }
    }

    public GScene getScene() {
        return this.scene;
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        destroyCurrentScene();
        this.surfaceHolder = getHolder();

        try {
//            this.scene = (GScene)sceneType.getDeclaredConstructor(GSize.class).newInstance(new GSize(480, 800));
            this.scene = (GScene)sceneType.newInstance();
            this.scene.init(new GSize(this.resolution.width, this.resolution.height));
            this.scene.enable = true;
        } catch (Exception e) {e.printStackTrace();}
        this.sceneThread = new Thread(this.scene, "gameLoop");
        this.sceneThread.setDaemon(false);
        this.sceneThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder surfaceHolder, int i, int i1, int i2) {}

    @Override
    public void surfaceDestroyed(SurfaceHolder surfaceHolder) {
        destroyCurrentScene();
    }
}


