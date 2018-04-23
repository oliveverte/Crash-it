package com.olivierpicard.crachit.Shuttle;

import android.graphics.Color;

import com.olivierpicard.crachit.DataBaseHandler;
import com.olivierpicard.crachit.GameScene;
import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GVector;
import com.olivierpicard.crachit.IRestaurable;
import com.olivierpicard.crachit.R;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class ShuttleEnemiesGenerator implements IRestaurable {
    private class ShuttleConf {
        public Stats stats;
        public int color;
        public String imageName;

        public ShuttleConf(Stats stats, int color, String imageName) {
            this.stats = stats;
            this.color = color;
            this.imageName = imageName;
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
        final int xPos = GInterval.random(0, (int)this.scene.getSize().width);
        final int image = randomEnemyShuttle(1, NUMBER_OF_IMAGE_SHUTTLE);
        final ShuttleConf shuttle_info = determineShuttleGlobalInformation(image);
        final ShuttleEnemy shuttle = new ShuttleEnemy(image,
                shuttle_info.color, shuttle_info.stats, shuttle_info.imageName, this.target);
        shuttle.setPosition(new GPoint(xPos, yPos));
        this.scene.addChild(shuttle);
    }


    private int randomEnemyShuttle(int imageIdFrom, int imageIdTo) {
        final String imageName = "ennemy_"
                + String.valueOf(GInterval.random(imageIdFrom, imageIdTo));
        return getImageID(imageName);
    }

    public int getImageID(String bitmapName) {
        return GTools.resources.getIdentifier(bitmapName,
                "drawable", "com.olivierpicard.crachit");
    }


    private ShuttleConf determineShuttleGlobalInformation(int imageID) {
        Stats stats = null;
        int color = Color.WHITE;
        String imageName = "";

        switch (imageID) {
            case R.drawable.ennemy_1:
                stats = new Stats(90, 15,
                        new ShootStats((long)(1.0/5*1000), 60));
                color = Color.YELLOW;
                imageName = "ennemy_1";
                break;
            case R.drawable.ennemy_2:
                stats = new Stats(100, 10,
                        new ShootStats((long)(1.0/3*1000), 30));
                color = Color.CYAN;
                imageName = "ennemy_2";
                break;
            case R.drawable.ennemy_3:
                stats = new Stats(120, 10,
                        new ShootStats((long)(1.0/4*1000), 25));
                color = Color.CYAN;
                imageName = "ennemy_3";
                break;
            case R.drawable.ennemy_4:
                stats = new Stats(230, 10,
                        new ShootStats((long)(1.0/3*1000), 25));
                color = Color.CYAN;
                imageName = "ennemy_4";
                break;
            case R.drawable.ennemy_5:
                stats = new Stats(90, 20,
                        new ShootStats((long)(1.0/6*1000), 25));
                color = Color.GREEN;
                imageName = "ennemy_5";
                break;
        }

        return new ShuttleConf(stats, color, imageName);
    }


    @Override
    public void restaure(DataBaseHandler.ItemRestaurationTable item) {
        final ShuttleConf shuttle_info = determineShuttleGlobalInformation(getImageID(item.option1));
        final ShuttleEnemy shuttle = new ShuttleEnemy(getImageID(item.option1),
                shuttle_info.color, shuttle_info.stats, shuttle_info.imageName, this.target);
        shuttle.setPosition(new GPoint(item.xPos, item.yPos));
        shuttle.setZPosition(item.zPosition);
        shuttle.setZRotation(item.zRotation);
        shuttle.direction = new GVector(item.dx, item.dy);
        shuttle.lifeBar.setValue(item.life);
        this.scene.addChild(shuttle);
    }
}
