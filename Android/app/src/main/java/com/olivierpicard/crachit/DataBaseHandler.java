package com.olivierpicard.crachit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by olivierpicard on 17/04/2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    public class ItemRestaurationTable implements Serializable {
        public String classType;
        public int gameID, zPosition, life;
        public float xPos, yPos, zRotation, dx, dy;
        public String option1;
    }

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrashitDB";
    private static final String TABLE_NAME_SCORES = "Scores";
    private static final String TABLE_NAME_GAMES = "Games";
    private static final String TABLE_NAME_RESTAURATION = "Restauration";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_DATE = "date";
    private static final String KEY_GAMEID = "gameID";
    private static final String KEY_LIFE = "life";
    private static final String KEY_XPOS = "xPos";
    private static final String KEY_YPOS = "yPos";
    private static final String KEY_DXPOS = "dxPos";
    private static final String KEY_DYPOS = "dyPos";
    private static final String KEY_ZPOSITION = "zPosition";
    private static final String KEY_ZROTATION = "zRotation";
    private static final String KEY_OPTION1 = "option1";
    private static final String KEY_CLASSTYPE = "classType";


    public static DataBaseHandler reference;

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_SCORES + " ( "
                + KEY_ID + "  integer primary key autoincrement, "
                + KEY_SCORE + " integer, "
                + KEY_DATE + " text)";
        sqLiteDatabase.execSQL(CREATION_TABLE);



        CREATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_GAMES + " ( "
                + KEY_ID + "  integer primary key autoincrement, "
                + KEY_SCORE + " integer, "
                + KEY_DATE + " text)";
        sqLiteDatabase.execSQL(CREATION_TABLE);

        CREATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME_RESTAURATION + " ( "
                + KEY_ID + "  integer primary key autoincrement, "
                + KEY_GAMEID + " integer, "
                + KEY_CLASSTYPE + " text, "
                + KEY_LIFE + " integer, "
                + KEY_XPOS + " float, "
                + KEY_YPOS + " float, "
                + KEY_DXPOS + " float, "
                + KEY_DYPOS + " float, "
                + KEY_ZPOSITION + " integer, "
                + KEY_ZROTATION + " float, "
                + KEY_OPTION1 + " text)";
        sqLiteDatabase.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCORES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GAMES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_RESTAURATION);
        this.onCreate(sqLiteDatabase);
    }

    public void remove_score(int id) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_SCORES, "id = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


    public List<CellStruct> getAll_score() {
        List<CellStruct> cells = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME_SCORES + " ORDER BY " + KEY_SCORE + " DESC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query, null);
        }catch (Exception e) { return cells; }
        CellStruct cell = null;

        if (cursor != null &&
                cursor.moveToFirst()) {
            do {
                cell = new CellStruct();
                cell.id = Integer.parseInt(cursor.getString(0));
                cell.score = Integer.parseInt(cursor.getString(1));
                cell.date = cursor.getString(2);
                cells.add(cell);
            } while (cursor.moveToNext());
        }
        return cells;
    }


    public void add_score(CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        db.insert(TABLE_NAME_SCORES,null, values);
        db.close();
    }


    public int set_score(int id, CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        int i = db.update(TABLE_NAME_SCORES,
                values,
                "id = ?", // selections
                new String[] { String.valueOf(id) });
        db.close();
        return i;
    }




    //------------------------ GameSaved ------------------------------





    public CellStruct get_game(int id) {
        String query = "SELECT  * FROM " + TABLE_NAME_GAMES + " WHERE " + KEY_ID + " = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query, null);
        }catch (Exception e) { return null; }

        CellStruct cell = new CellStruct();

        if (cursor != null &&
            cursor.moveToFirst()) {
            cell.id = Integer.parseInt(cursor.getString(0));
            cell.score = Integer.parseInt(cursor.getString(1));
            cell.date = cursor.getString(2);
        }
        return cell;
    }


    public void remove_game(int id) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_GAMES, "id = ?",
                new String[] { String.valueOf(id) });
        db.delete(TABLE_NAME_RESTAURATION, "id = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


    public List<CellStruct> getAll_game() {
        List<CellStruct> cells = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME_GAMES;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query, null);
        }catch (Exception e) { return cells; }
        CellStruct cell = null;

        if (cursor != null && cursor.moveToFirst()) {
            do {
                cell = new CellStruct();
                cell.id = Integer.parseInt(cursor.getString(0));
                cell.score = Integer.parseInt(cursor.getString(1));
                cell.date = cursor.getString(2);
                cells.add(cell);
            } while (cursor.moveToNext());
        }
        return cells;
    }


    public void add_game(CellStruct cell, List<ItemRestaurationTable> items) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);
        db.insert(TABLE_NAME_GAMES,null, values);

        int lastID = 0;
        String query = "SELECT * FROM " + TABLE_NAME_GAMES + " ORDER BY " + KEY_ID + " DESC LIMIT 1";
        Cursor cursor = null;
        try{ cursor = db.rawQuery(query, null); }catch (Exception e) {e.printStackTrace(); return;}

        if (cursor != null && cursor.moveToFirst())
            lastID = cursor.getInt(0);

        for (ItemRestaurationTable item : items) {
            values = new ContentValues();
            values.put(KEY_GAMEID, lastID);
            values.put(KEY_CLASSTYPE, item.classType);
            values.put(KEY_LIFE, item.life);
            values.put(KEY_XPOS, item.xPos);
            values.put(KEY_YPOS, item.yPos);
            values.put(KEY_DXPOS, item.dx);
            values.put(KEY_DYPOS, item.dy);
            values.put(KEY_ZPOSITION, item.zPosition);
            values.put(KEY_ZROTATION, item.zRotation);
            values.put(KEY_OPTION1, item.option1);
            db.insert(TABLE_NAME_RESTAURATION,null, values);
            System.out.println();
        }
        db.close();
    }




    public List<ItemRestaurationTable> getItemsToRestaure(int gameID) {
        List<ItemRestaurationTable> items = new ArrayList<>();
        String query = "SELECT * FROM " + TABLE_NAME_RESTAURATION + " WHERE " + KEY_GAMEID + " = " + gameID;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = null;
        try{
            cursor = db.rawQuery(query, null);
        }catch (Exception e) { return items; }
        ItemRestaurationTable item = null;

        if (cursor != null &&
                cursor.moveToFirst()) {
            do {
                item = new ItemRestaurationTable();
                item.gameID = cursor.getInt(1);
                item.classType = cursor.getString(2);
                item.life = cursor.getInt(3);
                item.xPos = cursor.getFloat(4);
                item.yPos = cursor.getFloat(5);
                item.dx = cursor.getFloat(6);
                item.dy = cursor.getFloat(7);
                item.zPosition = cursor.getInt(8);
                item.zRotation = cursor.getFloat(9);
                item.option1 = cursor.getString(10);
                items.add(item);
            } while (cursor.moveToNext());
        }

        return items;
    }

}