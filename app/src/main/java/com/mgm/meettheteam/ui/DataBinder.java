package com.mgm.meettheteam.ui;

import com.bumptech.glide.Glide;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Utils for data binding
 *
 * Created by michaelmaitlen on 2/13/18.
 */
public final class DataBinder {

    private DataBinder() {
        //NO-OP
    }

    @BindingAdapter("imageUrl")
    public static void setImageUrl(ImageView imageView, String url) {
        Context context = imageView.getContext();
        Glide.with(context).load(url).into(imageView);
    }
}