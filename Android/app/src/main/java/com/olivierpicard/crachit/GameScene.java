package com.olivierpicard.crachit;

import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;

/**
 * Created by olivierpicard on 03/04/2018.
 */

public class GameScene extends GScene {


    public GameScene(GSize size) {
        super(size);
        System.out.println("scene");
    }

    public void start() {

    }

    @Override
    public void update(Double currentTime) {

    }

    @Override
    public void touchDown(GPoint pos) {
        super.touchDown(pos);
    }

    @Override
    public void touchUp(GPoint pos) {
        super.touchUp(pos);
    }

}
