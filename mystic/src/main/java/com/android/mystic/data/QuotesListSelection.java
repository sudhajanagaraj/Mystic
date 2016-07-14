package com.android.mystic.data;

import android.content.Context;
import android.content.Intent;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import com.android.mystic.R;
import com.android.mystic.log.MysticLog;
import com.android.mystic.ui.FullscreenActivity;
import com.android.mystic.ui.MastersListActivity;

import java.util.HashMap;

/**
 * Created by Sudha on 7/14/2016.
 */
public class QuotesListSelection implements IListSelection {

    View mView;
    Context mContext;
    private static SparseBooleanArray imgFavHash = new SparseBooleanArray();
    public QuotesListSelection(Context context, View view) {
        this.mContext = context;
        mView = view;
    }
    @Override
    public void imgFavoriteSelected(int resFavId) {

         ImageView imgFav = (ImageView) mView.findViewById(resFavId);
        if(imgFavHash.get(resFavId,true)) {
            imgFavHash.put(resFavId,false);
            imgFav.setImageResource(R.mipmap.ic_star_black_36dp);
        }
        else {
            imgFavHash.put(resFavId,true);
            imgFav.setImageResource(R.mipmap.ic_star_border_black_36dp);
        }
    }

    @Override
    public void textDescriptionSelected() {
        startQuotesFullScreen();
    }

    @Override
    public void imgProfileSelected() {
        Intent intent = new Intent(mContext, MastersListActivity.class);
        mContext.startActivity(intent);
    }

    void startQuotesFullScreen() {
        Intent intent = new Intent();
        intent.setClass(mContext, FullscreenActivity.class);
        mContext.startActivity(intent);
    }
}
