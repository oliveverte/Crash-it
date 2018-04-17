package com.olivierpicard.crachit;

import android.content.Intent;
import android.os.Bundle;

import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends GActivity {
    public static final int RESUME_ACTIVITY_CODE = 1;
    public MainActivity() {
        super(R.id.sceneViewController, R.layout.activity_main, GameScene.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        DataBaseHandler.reference = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RESUME_ACTIVITY_CODE && resultCode == RESULT_OK) {
            GameScene gameScene = (GameScene) getScene();
            gameScene.start();
        }
    }


}
