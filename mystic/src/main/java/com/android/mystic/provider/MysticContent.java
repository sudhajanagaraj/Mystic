package com.android.mystic.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by janagaraj.veluchamy on 7/6/2016.
 */
public final class MysticContent {

    public final static String TABLE_NAME_MASTER = "Master";

    public final static String TEXT_TYPE = " TEXT";
    public final static String DATE_TYPE = " Date";
    public final static String INTEGER_TYPE = " INTEGER";
    public final static String UNIQUE = " UNIQUE";
    public final static String COMMA_SEP = " ,";
    public final static String PRIMARY_KEY = " PRIMARY KEY";


    public abstract class Master implements BaseColumns {
        /*Master table */
        public final static String COLUMN_NAME_MASTER_NAME = "name";
        public final static String COLUMN_NAME_MASTER_SPOUSE_NAME = "spouse";
        public final static String COLUMN_NAME_DOB = "dob";
        public final static String COLUMN_NAME_DOD = "dod";
        public final static String COLUMN_NAME_ABOUT_LIFE = "aboutlife";
        public final static String COLUMN_NAME_PIC1 = "pic1";
        public final static String COLUMN_NAME_PIC2 = "pic2";
        public final static String COLUMN_NAME_WIKI_LINK = "wikilink";
        public final static String COLUMN_NAME_DISCIPLE = "disciple";

        public final static int COLUMN_INDEX_MASTER_ID = 0;
        public final static int COLUMN_INDEX_MASTER_NAME = 1;
        public final static int COLUMN_INDEX_MASTER_SPOUSE_NAME = 2;
        public final static int COLUMN_INDEX_DOB = 3;
        public final static int COLUMN_INDEX_DOD = 4;
        public final static int COLUMN_INDEX_ABOUT_LIFE = 5;
        public final static int COLUMN_INDEX_PIC1 = 6;
        public final static int COLUMN_INDEX_PIC2 = 7;
        public final static int COLUMN_INDEX_WIKI_LINK = 8;
        public final static int COLUMN_INDEX_DISCIPLE = 9;
    }


    public abstract class Quotes implements BaseColumns {
        /*Quotes table */
        public final static String COLUMN_NAME_QUOTES_DESC = "quoteDesc";
        public final static String COLUMN_NAME_MASTER_ID = "masterId";
        public final static String COLUMN_NAME_FAVORITE = "favorite";
        public final static String COLUMN_NAME_SHOWN = "seen";

        public final static int COLUMN_INDEX_QUOTES_DESC = 0;
        public final static int COLUMN_INDEX_MASTER_ID = 1;
        public final static int COLUMN_INDEX_FAVORITE = 2;
        public final static int COLUMN_INDEX_SHOWN = 3;
    }
}