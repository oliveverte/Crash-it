package com.olivierpicard.crachit;

import android.os.Bundle;

import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends GActivity {


    public MainActivity() {
        super(R.id.sceneViewController, R.layout.activity_main, GameScene.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
