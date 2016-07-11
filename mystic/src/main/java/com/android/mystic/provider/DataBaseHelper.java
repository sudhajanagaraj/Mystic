package com.android.mystic.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import com.android.mystic.R;
import com.android.mystic.log.MysticLog;
import com.android.mystic.util.Util;

import java.io.IOException;

/**
 * Created by janagaraj.veluchamy on 6/22/2016.
 */
public class DataBaseHelper extends SQLiteOpenHelper{

    private static Context mContext = null;
    private static  SQLiteDatabase mSqlDB = null;
    private static boolean bDbExists = false;

    private static String DATABASE_PATH = null;
    private final static String  DATABASE_NAME = "MysticProvider.db";
    private final static int  DATABASE_VERSION = 1;



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + MysticContent.TABLE_NAME_MASTER ;

    public DataBaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        mContext = context;
        MysticLog.d("DataBaseHelper called !!!!");
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
       // createDataBase();
        sqLiteDatabase.execSQL(DBUtility.CREATE_MASTER_TABLE);
        sqLiteDatabase.execSQL(DBUtility.CREATE_QUOTES_TABLE);

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

    public void createDataBase() {
        bDbExists = isDBExists();
        if(!bDbExists){
            try {
                String srcDbFile = mContext.getAssets() + DATABASE_NAME;
                String outDbFile = getDataBaseFile();
                Util.IOCopy(outDbFile, srcDbFile);
                bDbExists = true;
            } catch (IOException e) {
                MysticLog.e("Exception during createDataBase () !!!!" + e);
                bDbExists = false;
            }
        }
    }

    private boolean isDBExists(){
        try{
            String dbPath = getDataBaseFile();
            mSqlDB = SQLiteDatabase.openDatabase(dbPath, null, SQLiteDatabase.OPEN_READONLY);
        }catch(SQLiteException e){
            MysticLog.e("isDBExists () !!!!" + e);
        } finally {
            if(mSqlDB != null)
                mSqlDB.close();
        }
        return mSqlDB != null ? true : false;
    }

    String getDataBaseFile() {
        String dbFile = null;
        DATABASE_PATH = "/data/data/" + mContext.getResources().getString(R.string.application) + "/databases/";
        dbFile = DATABASE_PATH + DATABASE_NAME;
        MysticLog.d("getDataBaseFile () : " + dbFile);
        return dbFile;
    }

}
