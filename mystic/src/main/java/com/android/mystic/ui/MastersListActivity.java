package com.android.mystic.ui;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.android.mystic.R;
import com.android.mystic.adapter.MasterListAdapter;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.data.MysticConstants;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;

import java.util.ArrayList;
import java.util.List;

public class MastersListActivity extends AppCompatActivity  implements LoaderManager.LoaderCallbacks {

    ListView listView;
    List<ListRowItems> rowItems;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masters_list);

        listView = (ListView) findViewById(R.id.listView_Masters);

        getLoaderManager().initLoader(MysticConstants.MASTERS_LIST_LOADER_ID, null, this);
    }

    // https://developer.android.com/guide/components/loaders.html#summary
    String[]  mProjection = {
            MysticContent.Master._ID,
            MysticContent.Master.COLUMN_NAME_MASTER_NAME,
            MysticContent.Master.COLUMN_NAME_ABOUT_LIFE
    };

    @Override
    public Loader onCreateLoader(int loaderId, Bundle bundle) {

        if(MysticConstants.MASTERS_LIST_LOADER_ID == loaderId) {

            return new CursorLoader(MastersListActivity.this,
                    ProviderUtility.MASTER_URI,
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
                    String name = cursor.getString(MysticContent.Master.COLUMN_INDEX_MASTER_NAME);
                    String decr = cursor.getString(MysticContent.Master.COLUMN_INDEX_ABOUT_LIFE);
                    int id = cursor.getInt(MysticContent.Master.COLUMN_INDEX_MASTER_ID);
                    ListRowItems item = new ListRowItems(id,R.drawable.ic_launcher,name,decr);
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

        MasterListAdapter adapter = new MasterListAdapter(this,R.layout.listview_quotes, rowItems );
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
