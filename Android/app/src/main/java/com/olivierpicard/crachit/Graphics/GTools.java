package com.olivierpicard.crachit.Graphics;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.util.DisplayMetrics;

import java.util.ArrayList;
import java.util.List;

/**
 * Panel d'outil pour faciliter le développement
 * Created by olivierpicard on 02/04/2018.
 */

public class GTools {
    public static DisplayMetrics screenMetrics = new DisplayMetrics();
    public static Resources resources;
    public static IGActivitySwitchable activitySwitcher;


    public static GSize fromSceneToScreenSize(GSize screenSize, GSize sceneSize) {
        return new GSize(sceneSize.width * screenSize.width,
                sceneSize.height * sceneSize.height);
    }


    public static GPoint fromSceneToScreenPos(GSize screenSize, GPoint scenePos) {
        return new GPoint(scenePos.x * screenSize.width,
                (1 - scenePos.y) * screenSize.height);
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


    public static List<Integer> getListOfDrawableRessouce(String ressouceBaseName, int idFrom, int idTo) {
        List<Integer> listOfRessouceID = new ArrayList<>();
        for(int i = idFrom; i < idTo + 1; i++) {
            final int resourceId = GTools.resources.getIdentifier(ressouceBaseName + i,
                    "drawable",
                    "com.olivierpicard.crachit");
            listOfRessouceID.add(resourceId);
        }
        return listOfRessouceID;
    }

}
