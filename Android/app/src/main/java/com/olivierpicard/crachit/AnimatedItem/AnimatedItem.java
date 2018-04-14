package com.olivierpicard.crachit.AnimatedItem;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.olivierpicard.crachit.Graphics.GSize;
import com.olivierpicard.crachit.MovingItem;
import com.olivierpicard.crachit.Tools;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by olivierpicard on 06/04/2018.
 */

public class AnimatedItem extends MovingItem {
    int loop;
    int counterLoop;
    long latency;
    List<Bitmap> bitmaps;
    boolean enableAnimation;
    private long previous_frame;
    private int index_current_texture;
    private boolean delete_atEnd;


    public AnimatedItem(List<Integer> bitmapRessouceIDs, int numberOfLooping,
                 GSize size, long latency, boolean deleteAtEnd) {
        super(bitmapRessouceIDs.get(0), size);
        bitmaps = new ArrayList<>();
        this.loop = numberOfLooping;
        this.counterLoop = 0;
        for(int bitmapRessouceID : bitmapRessouceIDs)
            bitmaps.add(BitmapFactory.decodeResource(Tools.resources, bitmapRessouceID));
        this.latency = latency;
        this.previous_frame = 0;
        this.index_current_texture = 0;
        this.enableAnimation = true;
        this.delete_atEnd = deleteAtEnd;
    }


    @Override
    public void update(long currentTime) {
        super.update(currentTime);
        if(!this.enableAnimation) return;
        if(this.loop > 0 && this.counterLoop >= this.loop
                && this.delete_atEnd && this.getScene() != null) {
            this.getScene().removeChild(this);
        }

        if(currentTime - this.previous_frame < this.latency) return;

        if(this.index_current_texture + 1 >= this.bitmaps.size()){
            this.index_current_texture = 0;
            this.counterLoop += 1;
        } else this.index_current_texture += 1;

        setBitmap(this.bitmaps.get(this.index_current_texture));
        this.previous_frame = currentTime;
    }


}
