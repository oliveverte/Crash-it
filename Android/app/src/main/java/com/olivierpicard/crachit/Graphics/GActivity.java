package com.olivierpicard.crachit.Graphics;

/**
 * Created by olivierpicard on 16/04/2018.
 */

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;

import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.*;
import com.olivierpicard.crachit.R;

public class GActivity extends AppCompatActivity implements IGActivitySwitchable {
    private GSceneViewController sceneViewController;
    private int sceneViewControllerID, contentView;
    private Class sceneClass;


    protected GActivity(int sceneViewControllerID, int contentView, Class sceneClass) {
        this.sceneViewControllerID = sceneViewControllerID;
        this.sceneClass = sceneClass;
        this.contentView = contentView;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(this.contentView);
        GTools.resources = getResources();
        GTools.activitySwitcher = this;

        // Récupération de la taille de l'écran et la stock dans GTools
        this.getWindowManager().getDefaultDisplay().getMetrics(GTools.screenMetrics);
        sceneViewController = findViewById(this.sceneViewControllerID);
        sceneViewController.confScene(this.sceneClass);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        sceneViewController.onTouch(event);
        return super.onTouchEvent(event);
    }

    @Override
    public void switchActivity(Class activity) {}
    @Override
    public void switchActivity(Class activity, String Message) {}
}
