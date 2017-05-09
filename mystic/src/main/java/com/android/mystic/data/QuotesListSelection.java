package com.android.mystic.data;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ImageView;

import com.android.mystic.R;
import com.android.mystic.provider.MysticContent;
import com.android.mystic.provider.ProviderUtility;
import com.android.mystic.ui.MastersListActivity;
import com.android.mystic.ui.QuotesActivity;

/**
 * Created by Sudha on 7/14/2016.
 */
public class QuotesListSelection implements IListSelection {

    View mView;
    private Context mContext;
    private int quoteId;
    private static SparseBooleanArray imgFavHash = new SparseBooleanArray();

    public QuotesListSelection(Context context, int id, View view) {
        this.mContext = context;
        mView = view;
        quoteId = id;
    }

    @Override
    public void imgFavoriteSelected(int resFavId) {

        boolean toggle = false;
        ImageView imgFav = (ImageView) mView.findViewById(resFavId);
        if (imgFavHash.get(resFavId, true)) {
            imgFavHash.put(resFavId, false);
            imgFav.setImageResource(R.mipmap.ic_star_black_36dp);
            toggle = false;
        } else {
            imgFavHash.put(resFavId, true);
            imgFav.setImageResource(R.mipmap.ic_star_border_black_36dp);
            toggle = true;
        }
        new FavItemUpdate(toggle, quoteId).execute();
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
        intent.setClass(mContext, QuotesActivity.class);
        mContext.startActivity(intent);
    }

    class FavItemUpdate extends AsyncTask<Void, Void, Void> {
        boolean toggle;
        int id;
        FavItemUpdate(boolean value,int quoteId) {
            toggle = value;
            id = quoteId;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            ContentValues cv = new ContentValues();
            cv.put(MysticContent.Quotes.COLUMN_NAME_QUOTES_FAVORITE, toggle);

            String selection = MysticContent.Quotes._ID + "=?";
            String[] selectionArgs = {String.valueOf(id)};
            mContext.getContentResolver().update(ProviderUtility.QUOTES_URI, cv, selection, selectionArgs);
            return null;
        }
    }
}
