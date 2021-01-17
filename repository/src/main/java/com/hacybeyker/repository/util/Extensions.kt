package com.hacybeyker.repository.util

import java.text.SimpleDateFormat
import java.util.*

fun String.toDate(): Date? {
    val format = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'", Locale.ENGLISH)
    return format.parse(this)
}

fun Date.toString(): String {
    val outputFormat = SimpleDateFormat("dd-MM-yyyy", Locale.ENGLISH)
    return outputFormat.format(this)
}