package com.android.mystic.data;

import com.android.mystic.R;

/**
 * Created by janagaraj.veluchamy on 7/4/2016.
 */
public class ListRowItems {
    private String mTitle;
    private String mDescription;
    private int mProfilePicId;
    private int mImgFavResId;
    private int mId;

    public ListRowItems(int id, int mProfilePicId, String mTitle, String mDescription) {
        this.mId = id;
        this.mProfilePicId = mProfilePicId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImgFavResId = R.mipmap.ic_star_border_black_36dp;
    }
    public String getTitle() {
        return mTitle;
    }

    public void setTitle(String mTitle) {
        this.mTitle = mTitle;
    }

    public int getProfilePicId() {
        return mProfilePicId;
    }

    public void setProfilePicId(int mProfilePicId) {
        this.mProfilePicId = mProfilePicId;
    }


    public int getImgFavResId() {
        return mImgFavResId;
    }

    public void setImgFavResId(int mImgFavResId) {
        this.mImgFavResId = mImgFavResId;
    }
    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
