package com.olivierpicard.crachit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import com.olivierpicard.crachit.Graphics.*;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Récupération de la taille de l'écran et la stock en simplifié dans Tools
        DisplayMetrics screenSize = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(screenSize);
        Tools.screenSize = new GSize(screenSize.widthPixels, screenSize.heightPixels);
    }


}
