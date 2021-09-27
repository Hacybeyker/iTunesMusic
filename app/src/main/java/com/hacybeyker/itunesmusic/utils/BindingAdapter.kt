package com.hacybeyker.itunesmusic.utils

import android.widget.ArrayAdapter
import android.widget.ImageView
import androidx.appcompat.widget.AppCompatAutoCompleteTextView
import androidx.databinding.BindingAdapter


@BindingAdapter("srcImage")
fun srcImage(imageView: ImageView, url: String?) {
    url.let {
        imageView.load(it ?: "")
    }
}

@BindingAdapter("autoCompleteAdapter")
fun autoCompleteAdapter(view: AppCompatAutoCompleteTextView, adapter: ArrayAdapter<*>) {
    view.setAdapter(adapter)
}