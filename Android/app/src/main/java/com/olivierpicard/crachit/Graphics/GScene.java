package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GScene extends View{
    private List<GNode> children;

    private void init() {
        this.children = new ArrayList<>();
    }


    public GScene(Context context) {
        super(context);
        init();
    }

    public GScene(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    @Override
    protected void onDraw(Canvas canvas) {
        for(GNode child : children) {
            
        }
        super.onDraw(canvas);
    }


}
