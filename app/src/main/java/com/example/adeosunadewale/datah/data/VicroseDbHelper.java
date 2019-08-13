package com.example.adeosunadewale.datah.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
/**
 * Created by ADEOSUN ADEWALE on 10/27/2017.
 */

public class VicroseDbHelper extends SQLiteOpenHelper {

    private final static String DATABASE_NAME = "vicrose.db";
    private static final int DATABASE_VERSION = 1;

    public VicroseDbHelper(Context context){

        super(context,DATABASE_NAME, null, DATABASE_VERSION );

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //CREATES A STRING THAT CONTAINS THE SQL STATEMENT TO CREATE THE TABLE
        String SQL_CREATE_V_TABLE = "CREATE TABLE " + VicroseContract.VicEntry.TABLE_NAME + "(" +
                VicroseContract.VicEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                +  VicroseContract.VicEntry.COLUMN_NAME + " TEXT,"
                + VicroseContract.VicEntry.COLUMN_BK + " REAL,"
                + VicroseContract.VicEntry.COLUMN_Bst + " REAL,"
                + VicroseContract.VicEntry.COLUMN_FB + " REAL,"
                + VicroseContract.VicEntry.COLUMN_GL + " REAL,"
                + VicroseContract.VicEntry.COLUMN_Knel + " REAL,"
                + VicroseContract.VicEntry.COLUMN_lb + " REAL,"
                + VicroseContract.VicEntry.COLUMN_SI + " REAL,"
                + VicroseContract.VicEntry.COLUMN_Slv + " REAL,"
                + VicroseContract.VicEntry.COLUMN_Wst + " REAL,"
                + VicroseContract.VicEntry.COLUMN_UndBst + " REAL,"
                + VicroseContract.VicEntry.COLUMN_Sw + " REAL );";

        db.execSQL(SQL_CREATE_V_TABLE);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
