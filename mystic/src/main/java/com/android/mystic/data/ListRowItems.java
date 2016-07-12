package com.android.mystic.data;

/**
 * Created by janagaraj.veluchamy on 7/4/2016.
 */
public class ListRowItems {
    private String mTitle;
    private String mDescription;
    private int mProfilePicId;
    private int mImgFavResId;

    public ListRowItems(int mProfilePicId, String mTitle, String mDescription) {
        this.mProfilePicId = mProfilePicId;
        this.mTitle = mTitle;
        this.mDescription = mDescription;
        this.mImgFavResId = android.support.design.R.drawable.abc_ic_star_black_36dp;
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
