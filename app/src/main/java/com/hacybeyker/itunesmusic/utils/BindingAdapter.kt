package com.hacybeyker.itunesmusic.utils

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("srcImage")
fun srcImage(imageView: ImageView, url: String) {
    imageView.load(url)
}