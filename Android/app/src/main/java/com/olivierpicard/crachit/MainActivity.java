package com.olivierpicard.crachit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;

import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends AppCompatActivity {
    private GSceneViewController sceneViewController;
    private GameScene gameScene;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Tools.resources = getResources();

        // Récupération de la taille de l'écran et la stock dans Tools
        this.getWindowManager().getDefaultDisplay().getMetrics(Tools.screenMetrics);
        gameScene = new GameScene(new GSize(Tools.screenMetrics.widthPixels,
                Tools.screenMetrics.heightPixels));

        sceneViewController = findViewById(R.id.sceneViewController);
        sceneViewController.init(gameScene);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sceneViewController.onTouch(event);
        return super.onTouchEvent(event);
    }


}
