package com.olivierpicard.crachit;

import android.content.Intent;
import android.os.Bundle;

import com.olivierpicard.crachit.Graphics.*;

import java.util.Date;
import java.text.SimpleDateFormat;

public class MainActivity extends GActivity {

    public MainActivity() {
        super(R.id.sceneViewController, R.layout.activity_main, GameScene.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Scores.dbAdapter = new DataBaseHandler(this);
        super.onCreate(savedInstanceState);
    }

    @Override
    public void switchActivity(Class activity) {
        Intent intent = new Intent(this, Scores.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }

    @Override
    public void switchActivity(Class activity, String Message) {
        super.switchActivity(activity, Message);
    }


}
