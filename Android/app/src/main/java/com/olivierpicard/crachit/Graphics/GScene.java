package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.graphics.Canvas;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.olivierpicard.crachit.Tools;

import org.w3c.dom.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GScene extends GNode{
    private GScene() {}

    public GScene(GSize size) {
        super(size);
    }
}
