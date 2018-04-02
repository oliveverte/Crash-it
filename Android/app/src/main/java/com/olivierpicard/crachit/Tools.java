package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class Tools {
    // On cosidère que screenSize sera initialisé dès le début
    // Elle ne sera jamais null tout à long de sa vie
    public static GSize screenSize;


    public static GSize fromSceneToScreenSize(GSize sceneSize) {
        return new GSize(sceneSize.width * screenSize.width,
                sceneSize.height * screenSize.height);
    }


    public static GPoint fromSceneToScreenPos(GPoint scenePos) {
        return new GPoint(scenePos.x * screenSize.width,
                scenePos.y * screenSize.height);
    }
}
