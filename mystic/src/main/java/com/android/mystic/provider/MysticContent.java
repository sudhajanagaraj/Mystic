package com.android.mystic.provider;

import android.provider.BaseColumns;

/**
 * Created by janagaraj.veluchamy on 7/6/2016.
 */
public final class MysticContent {

    public final static String TABLE_NAME_MASTER = "Master";
    public final static String TABLE_NAME_QUOTES = "Quotes";

    public final static String BOOL_TYPE = " BOOL";
    public final static String TEXT_TYPE = " TEXT";
    public final static String DATE_TYPE = " Date";
    public final static String INTEGER_TYPE = " INTEGER";
    public final static String UNIQUE = "";//" UNIQUE";
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
        public final static int COLUMN_INDEX_MASTER_NAME = COLUMN_INDEX_MASTER_ID + 1;
        public final static int COLUMN_INDEX_MASTER_SPOUSE_NAME = COLUMN_INDEX_MASTER_NAME + 1;
        public final static int COLUMN_INDEX_DOB = COLUMN_INDEX_MASTER_SPOUSE_NAME + 1;
        public final static int COLUMN_INDEX_DOD = COLUMN_INDEX_DOB + 1;
        public final static int COLUMN_INDEX_ABOUT_LIFE = COLUMN_INDEX_DOD + 1;
        public final static int COLUMN_INDEX_PIC1 = COLUMN_INDEX_ABOUT_LIFE + 1;
        public final static int COLUMN_INDEX_PIC2 = COLUMN_INDEX_PIC1 + 1;
        public final static int COLUMN_INDEX_WIKI_LINK = COLUMN_INDEX_PIC2 + 1;
        public final static int COLUMN_INDEX_DISCIPLE = COLUMN_INDEX_WIKI_LINK + 1;
    }


    public abstract class Quotes implements BaseColumns {
        /*Quotes table */
        public final static String COLUMN_NAME_QUOTES_TITLE= "title";
        public final static String COLUMN_NAME_QUOTES_DESC = "quoteDesc";
        public final static String COLUMN_NAME_MASTER_ID = "masterId";
        public final static String COLUMN_NAME_QUOTES_FAVORITE = "favorite";
        public final static String COLUMN_NAME_QUOTES_SEEN = "seen";
        public final static String COLUMN_NAME_QUOTES_CATEGORY= "category";

        public final static int COLUMN_INDEX_QUOTES_ID = 0;
        public final static int COLUMN_INDEX_QUOTES_TITLE = COLUMN_INDEX_QUOTES_ID + 1;
        public final static int COLUMN_INDEX_QUOTES_DESC = COLUMN_INDEX_QUOTES_TITLE + 1;
        public final static int COLUMN_INDEX_MASTER_ID = COLUMN_INDEX_QUOTES_DESC + 1;
        public final static int COLUMN_INDEX_QUOTES_FAVORITE = COLUMN_INDEX_MASTER_ID + 1;
        public final static int COLUMN_INDEX_QUOTES_SEEN = COLUMN_INDEX_QUOTES_FAVORITE + 1;
        public final static int COLUMN_INDEX_QUOTES_CATEGORY = COLUMN_INDEX_QUOTES_SEEN + 1;
    }
}