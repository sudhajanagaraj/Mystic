package com.android.mystic.provider;

import android.content.ContentProviderOperation;
import android.content.ContentValues;
import android.content.Context;

import com.android.mystic.R;
import com.android.mystic.log.MysticLog;

import java.util.List;

import static java.lang.Math.random;

/**
 * Created by janagaraj.veluchamy on 7/11/2016.
 */
//todo - Unique fields to be properly defined for each table
public class DBUtility {

    protected final static String CREATE_MAIN_TABLE = "CREATE TABLE IF NOT EXISTS " +
            MysticContent.TABLE_NAME_TITLE + " (" +
            MysticContent.Titles._ID + MysticContent.INTEGER_TYPE + MysticContent.PRIMARY_KEY + MysticContent.COMMA_SEP +
            MysticContent.Titles.COLUMN_NAME_TITLE + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Titles.COLUMN_NAME_DESC + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Titles.COLUMN_NAME_QUOTE_ID + MysticContent.INTEGER_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Titles.COLUMN_NAME_MASTER_URI + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Titles.COLUMN_NAME_CARTOON_URI + MysticContent.TEXT_TYPE
            + " )";

    protected final static String CREATE_MASTER_TABLE = "CREATE TABLE IF NOT EXISTS " +
            MysticContent.TABLE_NAME_MASTER + " (" +
            MysticContent.Master._ID + MysticContent.INTEGER_TYPE + MysticContent.PRIMARY_KEY + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_MASTER_NAME + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_MASTER_SPOUSE_NAME + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DOB + MysticContent.DATE_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DOD + MysticContent.DATE_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_ABOUT_LIFE + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_PIC1 + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_PIC2 + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_WIKI_LINK + MysticContent.TEXT_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Master.COLUMN_NAME_DISCIPLE + MysticContent.TEXT_TYPE + " )";


    protected final static String CREATE_QUOTES_TABLE = "CREATE TABLE IF NOT EXISTS " +
            MysticContent.TABLE_NAME_QUOTES + " (" +
            MysticContent.Quotes._ID + MysticContent.INTEGER_TYPE + MysticContent.PRIMARY_KEY + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_TITLE + MysticContent.TEXT_TYPE +  MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_MASTER_ID + MysticContent.INTEGER_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_FAVORITE + MysticContent.BOOL_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_SEEN + MysticContent.BOOL_TYPE  +
            MysticContent.Quotes.COLUMN_NAME_QUOTES_CATEGORY + MysticContent.TEXT_TYPE
            + " )";

    public static int insertMasterTable(Context context) {
        int value = 1;
        if (context == null) {
            return -1;
        }
        String[] names = context.getResources().getStringArray(R.array.master_activity_names);
        String[] data = context.getResources().getStringArray(R.array.main_activity_desc);
        try {
            ContentValues cv = new ContentValues();
            for (int i = 0; i < names.length; i++) {
                cv.clear();
                cv.put(MysticContent.Master.COLUMN_NAME_MASTER_NAME, names[i]);
                cv.put(MysticContent.Master.COLUMN_NAME_ABOUT_LIFE, data[i]);
                context.getContentResolver().insert(ProviderUtility.MASTER_URI, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = -1;
        }
        MysticLog.d("insertMasterTable executed !!");
        return value;
    }

    public static int insertQuotesTable(Context context) {
        int value = 1;
        if (context == null) {
            return -1;
        }
        String[] titles = context.getResources().getStringArray(R.array.main_activity_titles);
        String[] desc = context.getResources().getStringArray(R.array.main_activity_desc);
        try {
            ContentValues cv = new ContentValues();
            for (int i = 0; i < desc.length; i++) {
                cv.clear();
                cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_TITLE, titles[0]);
                cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC, desc[i]);
                cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_MASTER_ID, 1);
                cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_FAVORITE, false);
                cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_SEEN, false);
                context.getContentResolver().insert(ProviderUtility.QUOTES_URI, cv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            value = -1;
        } finally {
        }
        MysticLog.d("insertQuotesTable executed !!");
        return value;
    }

    public static void insertTitleTable(Context context) {

        if(context == null) {
            return;
        }
        String[] titles = context.getResources().getStringArray(R.array.main_activity_titles);
        String[] desc = context.getResources().getStringArray(R.array.main_activity_desc);
        String[] mastersUri = context.getResources().getStringArray(R.array.main_activity_mastersUri);

        for(int i =0; i < titles.length; i++){
            ContentValues cv = new ContentValues();
            cv.put(MysticContent.Titles.COLUMN_NAME_TITLE, titles[i]);
            cv.put(MysticContent.Titles.COLUMN_NAME_DESC, desc[i]);
            cv.put(MysticContent.Titles.COLUMN_NAME_CARTOON_URI, mastersUri[i]);
            cv.put(MysticContent.Titles.COLUMN_NAME_MASTER_URI, mastersUri[i]);
            context.getContentResolver().insert(ProviderUtility.TITLE_URI, cv);
        }
        MysticLog.d("insertTitleTable executed !!");

    }
}
