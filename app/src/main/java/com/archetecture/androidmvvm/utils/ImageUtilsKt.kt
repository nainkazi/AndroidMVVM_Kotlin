package com.archetecture.androidmvvm.utils

import android.content.Context
import android.widget.ImageView

import com.archetecture.androidmvvm.R
import com.bumptech.glide.Glide

class ImageUtilsKt {


    fun loadImage(context: Context, imgPath: String?, imageView: ImageView) {
        try {
            Glide.with(context).load(imgPath).placeholder(R.drawable.place_holder).error(R.drawable.place_holder).into(imageView)
        } catch (e: Exception) {

        }

    }

}
