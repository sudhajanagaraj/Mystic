package com.android.mystic.application;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.AssetManager;
import android.content.res.Configuration;

import com.android.mystic.log.MysticLog;
import com.android.mystic.provider.DBUtility;

/**
 * Created by janagaraj.veluchamy on 6/23/2016.
 */
public class MysticApp extends Application {

    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
        MysticLog.init(this);
        MysticLog.i("OnCreate Called !!!!");
        //Build up Title table with initial values
        DBUtility.insertTitleTable(this);
        // Just for inserting records
        DBUtility.insertQuotesTable(this);

    }
    public static Context getContext() {
        return mContext;
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
    }

    @Override
    public AssetManager getAssets() {
        return super.getAssets();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        return super.getApplicationInfo();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }


}
