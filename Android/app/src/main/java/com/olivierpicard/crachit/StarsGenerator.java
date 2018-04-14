package com.olivierpicard.crachit;

import android.graphics.Color;

import com.olivierpicard.crachit.Graphics.GInterval;
import com.olivierpicard.crachit.Graphics.GPoint;
import com.olivierpicard.crachit.Graphics.GScene;
import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.Graphics.GVector;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivierpicard on 04/04/2018.
 */

public class StarsGenerator {
    private int stars_percent;
    private GameScene current_scene;
    GSize star_size;
    float speed_factor;
    GInterval opacity_range;

    StarsGenerator(GameScene scene,
                   int starsPercent,
                   GInterval opacityRange,
                   GSize starSize,
                   float speedFactor)
    {
        this.current_scene = scene;
        this.stars_percent = starsPercent;
        this.speed_factor = speedFactor;
        this.opacity_range = opacityRange;
        this.star_size = starSize;

        // Crée aléatoirement des étoiles sur l'écran
        // Pour ne pas commencer le jeu avec un écran vide
        for(int i = 0; i < starsPercent*10; i++) {
            final int randHeight = GInterval.random(0, Tools.screenMetrics.heightPixels);
            final int randWidth = GInterval.random(0, Tools.screenMetrics.widthPixels);
            MovingItem star = new MovingItem(null, Color.WHITE, this.star_size);
            star.speed_factor = this.speed_factor;
            star.setPosition(new GPoint(randWidth, randHeight));
            star.setZPosition(10);
            star.setColor(Tools.setColorOpacity(Color.WHITE, opacity_range.random()));
            this.current_scene.addChild(star);
        }
    }


    void generate() {
        // Si le nombre aléatoire n'est pas compris dans [0, percentage_of_stars]
        // alors on arrête, ne gérère pas l'étoile (pour le prochain
        // update si la chance est de notre côté)
        if(GInterval.random(0, 100) > stars_percent*MovingItem.base_moving_speed) return;

        // hauteur de l'écran + 10 pour être sûre que
        // l'étoile sera générer en dehors de l'écran
        int yPos = -10;
        int xPos = GInterval.random(0, Tools.screenMetrics.widthPixels);

        MovingItem star = new MovingItem(null, Color.WHITE, this.star_size);
        star.speed_factor = this.speed_factor;
        star.setPosition(new GPoint(xPos, yPos));
        star.setColor(Tools.setColorOpacity(Color.WHITE, opacity_range.random()));
        star.setZPosition(10);
        this.current_scene.addChild(star);
    }
}
