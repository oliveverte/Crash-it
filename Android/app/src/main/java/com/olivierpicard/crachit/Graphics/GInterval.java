package com.olivierpicard.crachit.Graphics;

import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by olivierpicard on 02/04/2018.
 */

public class GInterval {
    int min, max;

    private GInterval() {}

    public GInterval(int min, int max) {
        this.min = min;
        this.max = max;
    }

    public int random() {
        return ThreadLocalRandom.current().nextInt(min, max+1);
    }
}
