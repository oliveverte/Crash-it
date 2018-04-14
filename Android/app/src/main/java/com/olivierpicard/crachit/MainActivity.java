package com.olivierpicard.crachit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends AppCompatActivity {
    private GSceneViewController sceneViewController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GTools.resources = getResources();

        // Récupération de la taille de l'écran et la stock dans GTools
        this.getWindowManager().getDefaultDisplay().getMetrics(GTools.screenMetrics);
        sceneViewController = findViewById(R.id.sceneViewController);
        sceneViewController.confScene(GameScene.class);
    }



    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sceneViewController.onTouch(event);
        return super.onTouchEvent(event);
    }
}
