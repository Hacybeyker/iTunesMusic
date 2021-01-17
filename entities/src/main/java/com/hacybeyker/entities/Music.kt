package com.hacybeyker.entities

import java.io.Serializable
import java.util.*

data class Music(
    val artworkUrl100: String,
    val collectionName: String,
    val artistName: String,
    val trackName: String,
    val previewUrl: String,
    val releaseDate: Date,
    val primaryGenreName: String,
    val trackTimeMillis: Int,
    val collectionId: Int
) : Serializable {
    fun genreWithDate(): String {
        val calendar = Calendar.getInstance(Locale.ENGLISH)
        calendar.time = releaseDate
        return "$primaryGenreName - ${calendar[Calendar.YEAR]}"
    }

    fun minutesWithSeconds(): String {
        val result = trackTimeMillis.toTime()
        return "${result.first}:${result.second}"
    }
}