package com.android.mystic.provider;

import android.content.ContentValues;
import android.content.Context;

/**
 * Created by janagaraj.veluchamy on 7/11/2016.
 */
public class DBUtility {

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
            MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC + MysticContent.TEXT_TYPE + MysticContent.UNIQUE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_MASTER_ID + MysticContent.INTEGER_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_FAVORITE + MysticContent.BOOL_TYPE + MysticContent.COMMA_SEP +
            MysticContent.Quotes.COLUMN_NAME_SEEN + MysticContent.BOOL_TYPE  + " )";
            // category - where it belongs -- life, sex, money, and power
            //

    public static void insertMasterTable(Context context) {
        ContentValues cv = new ContentValues();
        cv.put(MysticContent.Master.COLUMN_NAME_ABOUT_LIFE, "Void");
        cv.put(MysticContent.Master.COLUMN_NAME_DISCIPLE, "iam");
        cv.put(MysticContent.Master.COLUMN_NAME_MASTER_NAME, "youare");
        cv.put(MysticContent.Master.COLUMN_NAME_MASTER_NAME, "youare");
        context.getContentResolver().insert(ProviderUtility.MASTER_URI, cv);
    }

    public static void insertQuotesTable(Context context) {
        ContentValues cv = new ContentValues();
        cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC, "This is totally void");
        cv.put(MysticContent.Quotes.COLUMN_NAME_MASTER_ID, 1);
        cv.put(MysticContent.Quotes.COLUMN_NAME_FAVORITE, false);
        cv.put(MysticContent.Quotes.COLUMN_NAME_SEEN, false);
        context.getContentResolver().insert(ProviderUtility.QUOTES_URI, cv);
    }
}
