package com.android.mystic.provider;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.net.Uri;

import com.android.mystic.log.MysticLog;

public class MysticProvider extends ContentProvider {

    private static Context mContext = null;
    private static DataBaseHelper mDbHelper = null;

    public MysticProvider() {
    }

    @Override
    public boolean onCreate() {
        MysticLog.d("OnCreate called !!!!");
        if(mDbHelper == null) {
            mContext = getContext();
            mDbHelper = new DataBaseHelper(mContext, null, null, 0);
        }
        return true;
    }


    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        int rowId = db.delete(MysticContent.TABLE_NAME_MASTER,selection, selectionArgs);
        MysticLog.d("delete called !!!!");
        return rowId;
    }

    @Override
    public String getType(Uri uri) {
        String matchedUri = null;
        MysticLog.d("getType called !!!!");
        return  matchedUri;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        // Gets the data repository in write mode
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        if(db == null) {
            MysticLog.d("Invalid Db info");
        }
        String tableName = null;
        long newRowId = 0;
        switch(ProviderUtility.getMatchURI(uri)) {
            case ProviderUtility.MASTER:
                tableName = uri.getLastPathSegment();
                break;
            default:
                MysticLog.d("There are no uri matching !!!");
                break;
        }
        try {
            if ((tableName != null) && (values != null) && (values.size() != 0)) {
                newRowId = db.insert(
                        tableName,
                        null,
                        values);
            }
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        } finally {
            MysticLog.d("Newly inserted Id :" + newRowId);
            if(db != null) {
                db.close();
            }
        }
        return ProviderUtility.getUriForId(mContext,newRowId,uri);
    }


    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        if(db == null) {
            MysticLog.d("Invalid Db info");
        }
        String tableName = null;
        Cursor queryCursor  = null;
        switch(ProviderUtility.getMatchURI(uri)) {
            case ProviderUtility.MASTER:
                tableName = uri.getLastPathSegment();
                break;
            default:
                MysticLog.d("[query] There are no uri matching !!!");
                break;
        }
        try {
            if ((tableName != null)) {
                queryCursor = db.query(
                        tableName,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
            }
        } catch (SQLiteException sqle) {
            sqle.printStackTrace();
        } finally {
            MysticLog.d("query called !!!!");
        }
        return queryCursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        int count = db.update(
                MysticContent.TABLE_NAME_MASTER,
                values,
                selection,
                selectionArgs);

        MysticLog.d("update called !!!!");

        return count;
    }
}
