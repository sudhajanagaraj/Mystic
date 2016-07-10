package com.android.mystic.imageview;

import android.content.Context;
import android.graphics.Bitmap;
import android.widget.ImageView;

/**
 * Created by janagaraj.veluchamy on 6/23/2016.
 */
public class MysticImageView extends ImageView implements IMysticImageView {


    public MysticImageView(Context context) {
        super(context);
    }

    @Override
    public Bitmap getCircleImage() {
        return null;
    }

    @Override
    public Bitmap getSquareImage() {
        return null;
    }

    @Override
    public Bitmap getRectImage() {
        return null;
    }
}
