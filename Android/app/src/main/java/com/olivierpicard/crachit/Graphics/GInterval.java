package com.olivierpicard.crachit.Graphics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GInterval {
    int min, max;

    public GInterval(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int random() { return ThreadLocalRandom.current().nextInt(this.min, this.max); }

    public static int random(int min, int max) {
        return ThreadLocalRandom.current().nextInt(min, max + 1);
    }
}
