package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.olivierpicard.crachit.Tools;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GSceneViewController extends View{
    private GScene scene;

    public GSceneViewController(Context context) {
        super(context);
    }


    public GSceneViewController(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public void init() {
        this.scene = new GScene(Tools.screenSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
