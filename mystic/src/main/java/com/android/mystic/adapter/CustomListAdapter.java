package com.android.mystic.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.mystic.R;
import com.android.mystic.data.ListRowItems;

import java.util.List;

/**
 * Created by janagaraj.veluchamy on 7/4/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    List<ListRowItems> mQuotesLists;
    private int mResId;
    public CustomListAdapter(Context context,int mResId, List<ListRowItems> quotesItem) {
        this.mContext = context;
        this.mQuotesLists = quotesItem;
        this.mResId = mResId;
    }

    @Override
    public int getCount() {
        if (mQuotesLists == null) {
            return 0;
        }
        return mQuotesLists.size();
    }

    @Override
    public Object getItem(int pos) {
        if (mQuotesLists == null) {
            return null;
        }
        return mQuotesLists.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        if (mQuotesLists == null) {
            return 0;
        }
        return mQuotesLists.indexOf(getItem(pos));
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = mInflater.inflate(mResId, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) view.findViewById(R.id.list_Description);
            holder.txtTitle = (TextView) view.findViewById(R.id.list_Title);
            holder.imageView = (ImageView) view.findViewById(R.id.list_ProfilePic);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        ListRowItems rowItem = (ListRowItems) getItem(position);

        holder.txtDesc.setText(rowItem.getDescription());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getProfilePicId());

        return view;
    }
}
