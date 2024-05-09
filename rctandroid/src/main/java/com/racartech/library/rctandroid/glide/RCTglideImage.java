package com.racartech.library.rctandroid.glide;


import android.content.Context;
import android.widget.ImageButton;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;

public class RCTglideImage {

    public static void loadImageFromPath(Context context, String imagePath, ImageView imageView) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load("file://" + imagePath)
                .apply(requestOptions)
                .into(imageView);
    }

    public static void loadImageFromPath(Context context, String imagePath, ImageButton image_button) {
        RequestOptions requestOptions = new RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(context)
                .load("file://" + imagePath)
                .apply(requestOptions)
                .into(image_button);
    }



}
