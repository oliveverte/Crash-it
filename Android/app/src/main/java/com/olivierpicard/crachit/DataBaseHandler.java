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
    private static final String DATABASE_NAME = "TacheDB";
    private static final String TABLE_NAME = "Tache";
    private static final String KEY_ID = "id";
    private static final String KEY_SCORE = "score";
    private static final String KEY_DATE = "date";
    private static final String[] COLUMNS = { KEY_ID, KEY_SCORE, KEY_DATE};

    public DataBaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        onCreate(this.getWritableDatabase());
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATION_TABLE = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( "
                + KEY_ID + "  integer primary key autoincrement, "
                + KEY_SCORE + " integer, "
                + KEY_DATE + " text)";

        sqLiteDatabase.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(sqLiteDatabase);
    }

    public void remove(int id) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, "id = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }


    public List<CellStruct> getAll() {
        List<CellStruct> cells = new ArrayList<>();
        String query = "SELECT  * FROM " + TABLE_NAME;
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
                cell.score = Integer.parseInt(cursor.getString(1));
                cell.date = cursor.getString(2);
                cells.add(cell);
            } while (cursor.moveToNext());
        }
        return cells;
    }


    public void add(CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        db.insert(TABLE_NAME,null, values);
        db.close();
    }


    public int set(int id, CellStruct cell) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_SCORE, cell.score);
        values.put(KEY_DATE, cell.date);

        int i = db.update(TABLE_NAME,
                values,
                "id = ?", // selections
                new String[] { String.valueOf(id) });
        db.close();
        return i;
    }
}