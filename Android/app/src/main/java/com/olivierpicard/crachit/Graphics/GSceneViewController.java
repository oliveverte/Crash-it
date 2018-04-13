package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GSceneViewController extends SurfaceView implements SurfaceHolder.Callback {
    private Class sceneType;
    private GScene scene;
    public static SurfaceHolder surfaceHolder;
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
        final GPoint pos = new GPoint(ev.getX(), ev.getY());
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN :
                this.scene.touchDown(pos);
                break;
            case MotionEvent.ACTION_UP :
                this.scene.touchUp(pos);
                break;
            case MotionEvent.ACTION_MOVE :
                this.scene.touchMove(pos);
                break;
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder surfaceHolder) {
        destroyCurrentScene();
        this.surfaceHolder = getHolder();
        try {
            this.scene = (GScene)sceneType.newInstance();
        } catch (Exception e) {}
        this.sceneThread = new Thread(this.scene);
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


