package com.olivierpicard.crachit;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by olivierpicard on 17/04/2018.
 */


public final class CellStruct{
    public int id;
    public int score;
    public String date;

    public CellStruct() {}

    public CellStruct(int score, String date) {
        this.score = score;
        this.date = date;
    }

    public CellStruct(int score) {
        Date aujourdhui = new Date();
        SimpleDateFormat formater = new SimpleDateFormat("dd-MM-yy");
        this.date = formater.format(aujourdhui);
        this.score = score;
    }
}

