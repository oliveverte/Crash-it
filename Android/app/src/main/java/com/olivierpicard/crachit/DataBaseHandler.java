package com.olivierpicard.crachit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by olivierpicard on 17/04/2018.
 */

public class DataBaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "CrashitDB";
    private static final String TABLE_NAME_SCORES = "Scores";
    private static final String TABLE_NAME_GAMES = "Games";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_DATE = "date";
    private static final String[] COLUMNS = { KEY_ID, KEY_SCORE, KEY_DATE};

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
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SCORES);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_GAMES);
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
        String query = "SELECT  * FROM " + TABLE_NAME_SCORES;
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


    public void add_game(CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        db.insert(TABLE_NAME_GAMES,null, values);
        db.close();
    }


    public int set_game(int id, CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        int i = db.update(TABLE_NAME_GAMES,
                values,
                "id = ?", // selections
                new String[] { String.valueOf(id) });
        db.close();
        return i;
    }
}