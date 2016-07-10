package com.android.mystic.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.mystic.log.MysticLog;

/**
 * Created by janagaraj.veluchamy on 6/22/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private final static String  DATABASE_NAME = "MysticProvider.db";
    private final static int  DATABASE_VERSION = 1;

    private final static String CREATE_MASTER_TABLE = "CREATE TABLE IF NOT EXISTS " +
            MysticContent.TABLE_NAME_MASTER + " (" +
            MysticContent.Master._ID + MysticContent.INTEGER_TYPE + MysticContent.PRIMARY_KEY +  MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_MASTER_NAME + MysticContent.TEXT_TYPE + MysticContent.UNIQUE +  MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_MASTER_SPOUSE_NAME + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DOB + MysticContent.DATE_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DOD + MysticContent.DATE_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_ABOUT_LIFE + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_PIC1 + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_PIC2 + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_WIKI_LINK + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DISCIPLE + MysticContent.TEXT_TYPE + " )";


    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MysticContent.TABLE_NAME_MASTER ;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        MysticLog.d("DataBaseHelper called !!!!");

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_MASTER_TABLE);
        MysticLog.d("onCreate called !!!!");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        sqLiteDatabase.execSQL(SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);
        MysticLog.d("onUpgrade called !!!!");

    }

    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
        MysticLog.d("onDowngrade called !!!!");

    }
}
