package com.android.mystic.ui;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mystic.R;
import com.android.mystic.application.MysticApp;
import com.android.mystic.log.MysticLog;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;
import com.android.mystic.ui.listener.IScreenSwipe;
import com.android.mystic.ui.listener.OnSwipeTouchListener;

public class QuotesActivity extends AppCompatActivity implements IScreenSwipe {

    TextView tvQuotesContent;
    private static int nMaxQuotes = 0;
    private static int nCurrentId = 0;

    private Context  mContext = null;
    ;
    private ContentResolver mCr = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_fragment);
        tvQuotesContent = (TextView)findViewById(R.id.tvQuoteContent);
        tvQuotesContent.setOnTouchListener(new OnSwipeTouchListener(this, this));

        mContext = MysticApp.getContext();
        mCr = mContext.getContentResolver();
        setMaxQuotesCount();
        displayContent();
    }

    @Override
    public void onSwipeRight() {
        displayContent();
    }

    @Override
    public void onSwipeLeft() {
        displayContent();
    }

    @Override
    public void onSwipeBottom() {
    }

    @Override
    public void onSwipeTop() {
    }

    void displayContent() {
        new DisplayQuote().execute();
    }
    int getCurrentId() {
        if(nMaxQuotes <= 0){
            return -1;
        }
        if(nCurrentId == nMaxQuotes){
            nCurrentId = 0;
        }
        ++nCurrentId;
        MysticLog.d("Current ID : " + nCurrentId);
        return nCurrentId;
    }

    private void setMaxQuotesCount() {
        new Thread() {
            @Override
            public void run() {
                Cursor cursor = null;
                try {
                    cursor = mCr.query(ProviderUtility.QUOTES_URI, null, null,null, null);
                    if(cursor != null ){
                        nMaxQuotes = cursor.getCount();
                        MysticLog.d("MaxItems : " + nMaxQuotes);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }finally{
                    if(cursor != null) {
                        cursor.close();
                    }
                }
            }
        }.start();
    }

    private class DisplayQuote extends AsyncTask<Void,Void,Void > {

        private String[]  mProjection = {
                MysticContent.Quotes.COLUMN_NAME_QUOTES_TITLE,
                MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC
        };
        private String mSelection =  null;
        private String mTitle;
        private String mDescr;
        private int mFavQuote;

        private int COLUMN_INTEX_QUOTE_TITLE = 0;
        private int COLUMN_INTEX_QUOTE_DESCR = 1;


        @Override
        protected void onPreExecute() {
            getCurrentId();
            mSelection =   MysticContent.Quotes._ID + " = " + Integer.toString(nCurrentId);
        }

        @Override
        protected Void doInBackground(Void ... ids) {
            MysticLog.d("Current ID : " + nCurrentId);
            if(nCurrentId < 0) {
                return null;
            }
            Cursor cursor = null;
            try {
                cursor = mCr.query(ProviderUtility.QUOTES_URI, mProjection, mSelection, null, null);
                if(cursor != null && cursor.moveToFirst()){
                    mTitle = cursor.getString(COLUMN_INTEX_QUOTE_TITLE);
                    mDescr = cursor.getString(COLUMN_INTEX_QUOTE_DESCR);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(cursor != null) {
                    cursor.close();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            tvQuotesContent.setText(mDescr);
            MysticLog.d("Title : " + mTitle);
            MysticLog.d("Description : " + mDescr);
        }
    }
}
