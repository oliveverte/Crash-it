package com.olivierpicard.crachit;

import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class Tools {
    public static DisplayMetrics screenMetrics = new DisplayMetrics();
    public static Resources resources;


    public static GSize fromSceneToScreenSize(GSize sceneSize) {
        return new GSize(sceneSize.width * screenMetrics.widthPixels,
                sceneSize.height * screenMetrics.heightPixels);
    }


    public static GPoint fromSceneToScreenPos(GPoint scenePos) {
        return new GPoint(scenePos.x * screenMetrics.widthPixels,
                scenePos.y * screenMetrics.heightPixels);
    }


    public static Rect getRectFromSizeAndPos(GPoint pos, GSize size) {
        return new Rect(
                pos.x - size.width/2,
                pos.y - size.height/2,
                pos.x + size.width/2,
                pos.y + size.height/2
        );
    }
}
