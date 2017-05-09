package com.android.mystic.application;

import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mystic.R;
import com.android.mystic.adapter.TitleAdapter;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.data.MysticConstants;
import com.android.mystic.provider.DBUtility;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;
import com.android.mystic.ui.FavListActivity;
import com.android.mystic.ui.MastersListActivity;
import com.android.mystic.ui.QuotesListActivity;
import com.android.mystic.ui.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MysticMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener , LoaderManager.LoaderCallbacks  {

    ListView listView;
    List<ListRowItems> rowItems;
    ImageView imgCartoon = null;
    TextView tvCartoonTitle = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mystic_navigation);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
                DBUtility.insertMasterTable(MysticMainActivity.this);
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        listView = (ListView) findViewById(R.id.listView_Title);
        getLoaderManager().initLoader(MysticConstants.TITLE_LIST_LOADER_ID, null, this);

        imgCartoon =  (ImageView)findViewById(R.id.title_CarttoonPic);
        imgCartoon.setOnClickListener(mImageOnClickListener);

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.mystic_navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Intent settingIntent = new Intent(this, SettingsActivity.class);
            startActivity(settingIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        Intent intent = new Intent();
        switch(id) {
            case R.id.nav_quotes:
                intent.setClass(this, QuotesListActivity.class);
                break;
            case R.id.nav_masters:
                intent.setClass(this, MastersListActivity.class);
                break;
            case R.id.nav_cartoons:
                break;
            case R.id.nav_favourites:
                intent.setClass(this, FavListActivity.class);
                break;
            case R.id.nav_filters:
                break;
            case R.id.nav_share:
                break;
            case R.id.nav_send:
                break;
            default:
                break;
        }
        startActivity(intent);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private View.OnClickListener mImageOnClickListener = new View.OnClickListener() {
        public void onClick(View v) {
            Toast.makeText(MysticMainActivity.this,"ImageClicked",Toast.LENGTH_LONG).show();
        }
    };
    // https://developer.android.com/guide/components/loaders.html#summary

    String[]  mProjection = {
            MysticContent.Titles._ID,
            MysticContent.Titles.COLUMN_NAME_TITLE,
            MysticContent.Titles.COLUMN_NAME_DESC,
            MysticContent.Titles.COLUMN_NAME_MASTER_URI,
            MysticContent.Titles.COLUMN_NAME_CARTOON_URI,
    };

    @Override
    public Loader onCreateLoader(int loaderId, Bundle bundle) {

        if(MysticConstants.TITLE_LIST_LOADER_ID == loaderId) {

                return new CursorLoader(MysticMainActivity.this,
                        ProviderUtility.TITLE_URI,
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
                    //assing values
                    String descr = cursor.getString(MysticContent.Titles.COLUMN_INDEX_DESC);
                    String title = cursor.getString(MysticContent.Titles.COLUMN_INDEX_TITLE);
                    int id = cursor.getInt(MysticContent.Titles.COLUMN_INDEX_TITLE_ID);
                    ListRowItems item = new ListRowItems(id,R.drawable.ic_launcher,title,descr);
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

        TitleAdapter adapter = new TitleAdapter(this,R.layout.list_title_quotes, rowItems);
        listView.setAdapter(adapter);
    }

    @Override
    public void onLoaderReset(Loader loader) {

    }

}
