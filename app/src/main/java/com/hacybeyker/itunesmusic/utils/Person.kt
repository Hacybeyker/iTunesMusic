package com.hacybeyker.itunesmusic.utils

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Person(val lastName: String, val age: Int, val puto: Boolean) : Parcelable