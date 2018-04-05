package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GSceneViewController extends View {
    private GScene scene;

    public GSceneViewController(Context context) {
        super(context);
    }


    public GSceneViewController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }


    public void initScene(final GScene scene) {
        this.scene = scene;
        scene.didInitialized();
        Timer timer = new Timer();
        timer.schedule (new TimerTask() {
            public void run() { scene.update(23.0); postInvalidate();}
        }, 0, 16);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        this.scene.render(canvas);
        super.onDraw(canvas);
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

}


