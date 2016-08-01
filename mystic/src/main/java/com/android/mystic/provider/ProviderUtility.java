package com.android.mystic.provider;

import android.content.ContentUris;
import android.content.Context;
import android.content.UriMatcher;
import android.net.Uri;

/**
 * Created by Sudha on 7/10/2016.
 */
public class ProviderUtility {

    // used for the UriMacher

    public static final int TITLE = 0x01;
    public static final int TITLE_ID = TITLE + 0x01;

    public static final int MASTER = TITLE_ID + 0x01;
    public static final int MASTER_ID = MASTER + 0x01;

    public static final int QUOTES = MASTER_ID + 0x01;
    public static final int QUOTES_ID = QUOTES + 0x01;

    private final static String AUTHORITY = "com.android.mystic";
   // Content provider "content://com.android.mystic/"
    private final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/");

    private final static String TITLE_PATH = MysticContent.TABLE_NAME_TITLE;

    private final static String MASTER_PATH = MysticContent.TABLE_NAME_MASTER;

    private final static String QUOTES_PATH = MysticContent.TABLE_NAME_QUOTES;


    public final static Uri TITLE_URI = Uri.parse(CONTENT_URI + TITLE_PATH);

    public final static Uri MASTER_URI = Uri.parse(CONTENT_URI + MASTER_PATH);

    public final static Uri QUOTES_URI = Uri.parse(CONTENT_URI + QUOTES_PATH);


    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {

        sURIMatcher.addURI(AUTHORITY, TITLE_PATH, TITLE);
        sURIMatcher.addURI(AUTHORITY, TITLE_PATH + "/#", TITLE_ID);

        sURIMatcher.addURI(AUTHORITY, MASTER_PATH, MASTER);
        sURIMatcher.addURI(AUTHORITY, MASTER_PATH + "/#", MASTER_ID);

        sURIMatcher.addURI(AUTHORITY, QUOTES_PATH, QUOTES);
        sURIMatcher.addURI(AUTHORITY, QUOTES_PATH + "/#", QUOTES_ID);
    }

    public static int getMatchURI(Uri uri) {
       return sURIMatcher.match(uri);
    }

    public static Uri getUriForId(Context context, long id, Uri uri) {
        if (id > 0) {
            Uri itemUri = ContentUris.withAppendedId(uri, id);
            context. getContentResolver().
                    notifyChange(itemUri, null);
            return itemUri;
        }
        return Uri.EMPTY;
    }
}
