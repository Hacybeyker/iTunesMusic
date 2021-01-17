package com.hacybeyker.itunesmusic.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import com.hacybeyker.itunesmusic.R

fun ImageView.load(url: String) {
    if (url.isNotEmpty()) {
        Glide.with(this.context)
            .asBitmap()
            .load(url)
            .placeholder(R.drawable.ic_launcher_background)
            .error(R.drawable.ic_launcher_background)
            .centerCrop()
            .into(this)
    }
}