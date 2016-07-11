package com.android.mystic.application;

import android.content.Intent;
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
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.mystic.R;
import com.android.mystic.adapter.CustomListAdapter;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.provider.DBUtility;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;
import com.android.mystic.ui.FullscreenActivity;
import com.android.mystic.ui.MastersListActivity;
import com.android.mystic.ui.SettingsActivity;

import java.util.ArrayList;
import java.util.List;

public class MysticMainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    ListView listView;
    List<ListRowItems> rowItems;

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

        DBUtility.insertQuotesTable(this); // Just for inserting records


        listView = (ListView) findViewById(R.id.listView_Quotes);
        CustomListAdapter adapter = new CustomListAdapter(this,R.layout.listview_main, getResultsQuotes());
        listView.setAdapter(adapter);

        AdapterView.OnItemClickListener listener = new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
                Intent intent = new Intent();
                intent.setClass(MysticMainActivity.this,FullscreenActivity.class);
                startActivity(intent);
            }
        };
        listView.setOnItemClickListener(listener);
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
        Intent intent = null;
        if (id == R.id.nav_home) {
            // Handle the camera action
        } else if (id == R.id.nav_favourites) {

        } else if (id == R.id.nav_masters) {
            intent = new Intent(this, MastersListActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    List<ListRowItems> getResultsQuotes() {
        rowItems = new ArrayList<ListRowItems>();
        Cursor cursor = getContentResolver().query(ProviderUtility.QUOTES_URI,null,null,null,null,null);
        try {
            if (cursor != null && cursor.moveToFirst()) {
                do {
                    //assing values
                    String decr = cursor.getString(MysticContent.Quotes.COLUMN_INDEX_QUOTES_DESC);
                    int id = cursor.getInt(MysticContent.Quotes.COLUMN_INDEX_MASTER_ID);
                    ListRowItems item = new ListRowItems(R.drawable.ic_launcher,"Dummy",decr);
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
