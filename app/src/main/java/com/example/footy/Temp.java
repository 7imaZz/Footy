package com.example.footy;

import android.widget.ImageView;

public class Temp {

    private String imageUrl;
    private ImageView imageView;

    public Temp(String imageUrl, ImageView imageView) {
        this.imageUrl = imageUrl;
        this.imageView = imageView;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public ImageView getImageView() {
        return imageView;
    }
}
