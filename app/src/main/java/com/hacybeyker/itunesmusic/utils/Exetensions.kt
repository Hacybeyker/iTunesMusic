package com.hacybeyker.itunesmusic.utils

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager
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

fun Context.hideKeyboard() {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow((this as Activity).currentFocus?.windowToken, 0)
}