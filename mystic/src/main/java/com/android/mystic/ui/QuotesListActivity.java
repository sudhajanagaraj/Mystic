package com.android.mystic.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.android.mystic.R;
import com.android.mystic.adapter.QuotesListAdapter;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.data.MysticConstants;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;

import java.util.ArrayList;
import java.util.List;

public class QuotesListActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks{

    ListView listView;
    List<ListRowItems> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quotes_list);

        listView = (ListView) findViewById(R.id.listView_Quotes);

        getLoaderManager().initLoader(MysticConstants.QUOTES_LIST_LOADER_ID, null, this);
    }

    // https://developer.android.com/guide/components/loaders.html#summary

    String[]  mProjection = {
            MysticContent.Quotes._ID,
            MysticContent.Quotes.COLUMN_NAME_QUOTES_TITLE,
            MysticContent.Quotes.COLUMN_NAME_QUOTES_DESC,
            MysticContent.Quotes.COLUMN_NAME_QUOTES_FAVORITE,
    };

    @Override
    public Loader onCreateLoader(int loaderId, Bundle bundle) {

        if(MysticConstants.QUOTES_LIST_LOADER_ID == loaderId) {

            return new CursorLoader(QuotesListActivity.this,
                    ProviderUtility.QUOTES_URI,
                    mProjection,
                    null,
                    null,
                    null) ;
        }
        return null;
    }

    @Override
    public void onLoadFinished(Loader loader, Object obj) {

        rowItems = new ArrayList<ListRowItems>();
        Cursor cursor = (Cursor) obj;
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //assigning values
                    String descr = cursor.getString(MysticContent.Quotes.COLUMN_INDEX_QUOTES_DESC);
                    String title = cursor.getString(MysticContent.Quotes.COLUMN_INDEX_QUOTES_TITLE);
                    ListRowItems item = new ListRowItems(R.drawable.ic_launcher,title,descr);
                    rowItems.add(item);
                } while (cursor.moveToNext());
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (cursor != null) {
                cursor.close();
            }
        }

        QuotesListAdapter adapter = new QuotesListAdapter(this,R.layout.listview_quotes, rowItems );
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
