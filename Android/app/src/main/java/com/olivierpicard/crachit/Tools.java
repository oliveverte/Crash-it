package com.olivierpicard.crachit;

import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import com.olivierpicard.crachit.Graphics.GNode;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Panel d'outil pour faciliter le développement
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
                (1 - scenePos.y) * screenMetrics.heightPixels);
    }


    public static Rect getRectFromSizeAndPos(GPoint pos, GSize size) {
        return new Rect(
                (int)(pos.x - size.width/2),
                (int)(pos.y - size.height/2),
                (int)(pos.x + size.width/2),
                (int)(pos.y + size.height/2)
        );
    }

    public static int setColorOpacity(int baseColor, int opacity) {
        int R = (baseColor >> 16) & 0xff;
        int G = (baseColor >>  8) & 0xff;
        int B = (baseColor      ) & 0xff;
        int A = opacity;

        return (A & 0xff) << 24 | (R & 0xff) << 16 | (G & 0xff) << 8 | (B & 0xff);
    }

}
