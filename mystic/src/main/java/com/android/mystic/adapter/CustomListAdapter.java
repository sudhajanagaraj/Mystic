package com.android.mystic.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.mystic.R;
import com.android.mystic.data.ListRowItems;
import com.android.mystic.ui.FullscreenActivity;

import java.util.List;

/**
 * Created by janagaraj.veluchamy on 7/4/2016.
 */
public class CustomListAdapter extends BaseAdapter {

    private Context mContext;
    List<ListRowItems> mRowItem;
    private int mResId;
    public CustomListAdapter(Context context,int mResId, List<ListRowItems> mRowItem) {
        this.mContext = context;
        this.mRowItem = mRowItem;
        this.mResId = mResId;
    }

    @Override
    public int getCount() {
        if (mRowItem == null) {
            return 0;
        }
        return mRowItem.size();
    }

    @Override
    public Object getItem(int pos) {
        if (mRowItem == null) {
            return null;
        }
        return mRowItem.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        if (mRowItem == null) {
            return 0;
        }
        return mRowItem.indexOf(getItem(pos));
    }

    private class ViewHolder {
        ImageView imageView;
        TextView txtTitle;
        TextView txtDesc;
        ImageView imgFav;
    }
    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
        ViewHolder holder = null;

        LayoutInflater mInflater = (LayoutInflater)
                mContext.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = mInflater.inflate(mResId, null);
            holder = new ViewHolder();
            holder.txtDesc = (TextView) view.findViewById(R.id.list_Desc);
            holder.txtTitle = (TextView) view.findViewById(R.id.list_Title);
            holder.imageView = (ImageView) view.findViewById(R.id.list_ProfilePic);
            holder.imgFav = (ImageView) view.findViewById(R.id.list_imgFav);

            holder.txtDesc.setOnClickListener(mItemListener);
            holder.txtTitle.setOnClickListener(mItemListener);
            holder.imageView.setOnClickListener(mItemListener);
            holder.imgFav.setOnClickListener(mItemListener);
            view.setTag(holder);
        }
        else {
            holder = (ViewHolder) view.getTag();
        }

        ListRowItems rowItem = (ListRowItems) getItem(position);

        holder.txtDesc.setText(rowItem.getDescription());
        holder.txtTitle.setText(rowItem.getTitle());
        holder.imageView.setImageResource(rowItem.getProfilePicId());
        holder.imgFav.setImageResource(rowItem.getImgFavResId());

        return view;
    }

    // Create an anonymous implementation of OnClickListener
    private View.OnClickListener mItemListener = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

                case R.id.list_Desc:
                case R.id.list_Title:
                case R.id.list_ProfilePic:
                    Intent intent = new Intent();
                    intent.setClass(mContext, FullscreenActivity.class);
                    mContext.startActivity(intent);
                    break;
                case R.id.list_imgFav:
                    ImageView imgFav = (ImageView) v.findViewById(R.id.list_imgFav);
                    imgFav.setImageResource(R.mipmap.ic_stars_black_24dp);
                    break;
                default:
                    break;


            }
        }
    };

}
