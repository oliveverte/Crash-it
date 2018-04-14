package com.olivierpicard.crachit.Shuttle;

import android.graphics.Color;

import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.R;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ShuttleEnemiesGenerator {
    private class ShuttleConf {
        public Stats stats;
        public int color;

        public ShuttleConf(Stats stats, int color) {
            this.stats = stats;
            this.color = color;
        }
    }


    /** Nombre d'image de vaisseau comptenu dans l'atlas */
    private final int NUMBER_OF_IMAGE_SHUTTLE = 5;
    private final GameScene scene;
    private final ShuttlePlayer target;

    private long previous_generate_time;
    private long maxTime_before_generate = (long)(0.5*1000); // en milli seconde
    public int enemies_percent = 10;
    public boolean enable = false;


    public ShuttleEnemiesGenerator(GameScene scene, ShuttlePlayer target) {
        this.scene = scene;
        this.target = target;
        this.previous_generate_time = 0;
    }


    public void generate(long currentTime) {
        if(!this.enable) return;
        if(currentTime - this.previous_generate_time < this.maxTime_before_generate) return;
        this.previous_generate_time = currentTime;
        if(GInterval.random(0, 100) > this.enemies_percent) return;

        final int yPos = -10;
        final int xPos = GInterval.random(0, GTools.screenMetrics.widthPixels);
        final int image = randomEnemyShuttle(1, NUMBER_OF_IMAGE_SHUTTLE);
        final ShuttleConf shuttle_info = determineShuttleGlobalInformation(image);
        final ShuttleEnemy shuttle = new ShuttleEnemy(image,
                shuttle_info.color, shuttle_info.stats, this.target);
        shuttle.setPosition(new GPoint(xPos, yPos));
        this.scene.addChild(shuttle);
    }


    private int randomEnemyShuttle(int imageIdFrom, int imageIdTo) {
        final String imageName = "ennemy_"
                + String.valueOf(GInterval.random(imageIdFrom, imageIdTo));
        final int imageID = GTools.resources.getIdentifier(imageName,
                "drawable", "com.olivierpicard.crachit");
        return imageID;
    }


    private ShuttleConf determineShuttleGlobalInformation(int imageID) {
        Stats stats = null;
        int color = Color.WHITE;

        switch (imageID) {
            case R.drawable.ennemy_1:
                stats = new Stats(90, 15,
                        new ShootStats((long)(1.0/5*1000), 60));
                color = Color.YELLOW;
                break;
            case R.drawable.ennemy_2:
                stats = new Stats(100, 10,
                        new ShootStats((long)(1.0/3*1000), 30));
                color = Color.CYAN;
                break;
            case R.drawable.ennemy_3:
                stats = new Stats(120, 10,
                        new ShootStats((long)(1.0/4*1000), 25));
                color = Color.CYAN;
                break;
            case R.drawable.ennemy_4:
                stats = new Stats(230, 10,
                        new ShootStats((long)(1.0/3*1000), 25));
                color = Color.CYAN;
                break;
            case R.drawable.ennemy_5:
                stats = new Stats(90, 20,
                        new ShootStats((long)(1.0/6*1000), 25));
                color = Color.GREEN;
                break;
        }

        return new ShuttleConf(stats, color);
    }
}
