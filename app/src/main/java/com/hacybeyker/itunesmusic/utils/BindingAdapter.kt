package com.hacybeyker.itunesmusic.utils

import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.databinding.BindingAdapter


@BindingAdapter("srcImage")
fun srcImage(imageView: ImageView, url: String) {
    imageView.load(url)
}

@BindingAdapter("autoCompleteAdapter")
fun autoCompleteAdapter(view: AppCompatAutoCompleteTextView, adapter: ArrayAdapter<*>) {
    view.setAdapter(adapter)
}