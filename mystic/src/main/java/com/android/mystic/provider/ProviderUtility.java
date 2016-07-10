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
    public static final int MASTER = 0x01;
    public static final int MASTER_ID = 0x02;

    private final static String AUTHORITY = "com.android.mystic";
   // Content provider "content://com.android.mystic/"
    private final static Uri CONTENT_URI = Uri.parse("content://" + AUTHORITY + "/");

    private final static String MASTER_PATH = MysticContent.TABLE_NAME_MASTER;

    public final static Uri MASTER_URI = Uri.parse(CONTENT_URI + MASTER_PATH);

    private static final UriMatcher sURIMatcher = new UriMatcher(
            UriMatcher.NO_MATCH);
    static {
        sURIMatcher.addURI(AUTHORITY, MASTER_PATH, MASTER);
        sURIMatcher.addURI(AUTHORITY, MASTER_PATH + "/#", MASTER_ID);
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
