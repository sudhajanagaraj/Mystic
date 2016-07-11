package com.android.mystic.ui;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.mystic.R;
import com.android.mystic.adapter.CustomListAdapter;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;

import java.util.ArrayList;
import java.util.List;

public class MastersListActivity extends AppCompatActivity  {

    ListView listView;
    List<ListRowItems> rowItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_masters_list);

        listView = (ListView) findViewById(R.id.listView_Masters);
        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.listview_main, getResultsMasters());
        listView.setAdapter(adapter);
        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent intent = new Intent();
                intent.setClass(MastersListActivity.this,FullscreenActivity.class);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
    }

    List<ListRowItems> getResultsMasters() {
        rowItems = new ArrayList<ListRowItems>();
        Cursor cursor = getContentResolver().query(ProviderUtility.MASTER_URI,null,null,null,null,null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //assigning values
                    String name = cursor.getString(MysticContent.Master.COLUMN_INDEX_MASTER_NAME);
                    String decr = cursor.getString(MysticContent.Master.COLUMN_INDEX_ABOUT_LIFE);
                    ListRowItems item = new ListRowItems(R.drawable.ic_launcher,name,decr);
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
        return rowItems;
    }

}
