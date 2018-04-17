package com.olivierpicard.crachit;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;


/**
 * Created by olivierpicard on 17/04/2018.
 */

public class DBAdaptater {
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "crashit_datablase.db";

    private static final String TABLE_SaveDataS = "table_scores";
    public static final String COL_ID = "_id";
    public static final String COL_SCORE = "score";
    public static final String COL_DATE = "date";

    private static final String CREATE_DB =
            "create table " + TABLE_SaveDataS + " ("
                    + COL_ID + " integer primary key autoincrement, "
                    + COL_SCORE + " integer not null, "
                    + COL_DATE + " text);";

    private SQLiteDatabase mDB;
    private MyOpenHelper mOpenHelper;

    public DBAdaptater(Context context) {
        mOpenHelper = new MyOpenHelper(context, DB_NAME, null, DB_VERSION);
    }

    public void open() {
        mDB = mOpenHelper.getWritableDatabase();
    }

    public void close() {
        mDB.close();
    }

    public SaveData getSaveData(long id) throws SQLException {
        SaveData SaveData = null;

        Cursor c = mDB.query(TABLE_SaveDataS,
                new String[]{COL_ID, COL_SCORE, COL_DATE},
                COL_ID + " = " + id, null, null, null, null);

        if (c.getCount() > 0) {
            c.moveToFirst();
            SaveData = new SaveData(c.getInt(0), c.getString(1));
        }
        c.close();

        return SaveData;
    }

    public ArrayList<SaveData> getAllSaveDatas() {
        ArrayList<SaveData> SaveDatas = new ArrayList<SaveData>();

        Cursor c = mDB.query(TABLE_SaveDataS,
                new String[]{COL_ID, COL_SCORE, COL_DATE},
                null, null, null, null, null);

        c.moveToFirst();
        while (!c.isAfterLast()) {
            SaveDatas.add(
                    new SaveData(c.getInt(0), c.getString(1)));
            c.moveToNext();
        }
        c.close();

        return SaveDatas;
    }

    public long insertSaveData(int score, String date) {
        ContentValues values = new ContentValues();
        values.put(COL_SCORE, score);
        values.put(COL_DATE, date);
        return mDB.insert(TABLE_SaveDataS, null, values);
    }

    public int updateSaveData(long id, int titre, String date) {
        ContentValues values = new ContentValues();
        values.put(COL_SCORE, titre);
        values.put(COL_DATE, date);
        return mDB.update(TABLE_SaveDataS, values, COL_ID + "=" + id, null);
    }

    public int removeSaveData(long id) {
        return mDB.delete(TABLE_SaveDataS, COL_ID + " = " + id, null);
    }

    /**
     * Private class MyOpenHelper
     */
    private class MyOpenHelper extends SQLiteOpenHelper {
        public MyOpenHelper(Context context, String name,
                            SQLiteDatabase.CursorFactory cursorFactory, int version) {
            super(context, name, cursorFactory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(CREATE_DB);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("drop table " + TABLE_SaveDataS + ";");
            onCreate(db);
        }
    }
}