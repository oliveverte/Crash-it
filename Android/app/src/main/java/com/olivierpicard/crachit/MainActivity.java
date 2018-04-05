package com.olivierpicard.crachit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends AppCompatActivity {
    private GSceneViewController sceneViewController;
    private GameScene gameScene;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.resources = getResources();
        gameScene = new GameScene();

        // Récupération de la taille de l'écran et la stock dans Tools
        this.getWindowManager().getDefaultDisplay().getMetrics(Tools.screenMetrics);
        sceneViewController = findViewById(R.id.sceneViewController);
        sceneViewController.initScene(gameScene);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sceneViewController.onTouch(event);
        return super.onTouchEvent(event);
    }


}
