package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.entities.Music
import com.hacybeyker.repository.util.toDate
import java.text.SimpleDateFormat
import java.util.*

data class MusicResponse(
    @SerializedName("artworkUrl100") val artworkUrl100: String? = "",
    @SerializedName("collectionName") val collectionName: String? = "",
    @SerializedName("artistName") val artistName: String? = "",
    @SerializedName("trackName") val trackName: String? = "",
    @SerializedName("previewUrl") val previewUrl: String? = "",
    @SerializedName("releaseDate") val releaseDate: String? = "",
    @SerializedName("primaryGenreName") val primaryGenreName: String? = "",
    @SerializedName("trackTimeMillis") val trackTimeMillis: Int? = 0
) {
    fun toMusic(): Music {
        return Music(
            artworkUrl100 = artworkUrl100 ?: "",
            collectionName = collectionName ?: "",
            artistName = artistName ?: "",
            trackName = trackName ?: "",
            previewUrl = previewUrl ?: "",
            releaseDate = releaseDate?.toDate() ?: Calendar.getInstance().time,
            primaryGenreName = primaryGenreName ?: "",
            trackTimeMillis = trackTimeMillis ?: 0
        )
    }

    companion object {
        fun toMusicList(musics: List<MusicResponse>): List<Music> {
            val response = arrayListOf<Music>()
            for (item in musics)
                response.add(item.toMusic())
            return response
        }
    }
}