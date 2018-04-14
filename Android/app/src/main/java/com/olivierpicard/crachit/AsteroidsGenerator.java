package com.olivierpicard.crachit;

import com.olivierpicard.crachit.AnimatedItem.Asteroid;
import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GTools;

/**
 * Classe qui va générer des astéroides aléatoirement sur l'écran
 * Created by olivierpicard on 06/04/2018.
 */

public class AsteroidsGenerator {
    private GameScene scene;
    private long previousTime_generation;
    private long deltatTime_generation = (long)(0.4*1000); // en seconde
    public int asteroids_percent = 15;
    public boolean enable = false;


    AsteroidsGenerator(GameScene scene) {
        this.scene = scene;
        this.previousTime_generation = 0;
    }


    public void generate(long currentTime) {
        if(!this.enable) return;
        if(currentTime - this.previousTime_generation < this.deltatTime_generation) return;
        this.previousTime_generation = currentTime;
        if(GInterval.random(0, 100) > this.asteroids_percent) return;
        final GSize asteroid_size = new GSize(50, 50);
        final float yPos = -asteroid_size.height;
        final float xPos = (float)(GInterval.random((int)(asteroid_size.width/2),
                GTools.screenMetrics.widthPixels - (int)(asteroid_size.width/2)));

        final Asteroid asteroid = new Asteroid(asteroid_size);
        asteroid.setPosition(new GPoint(xPos, yPos));
        this.scene.addChild(asteroid);
    }

}
