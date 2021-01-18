package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.entities.Music
import com.hacybeyker.repository.util.toDate
import java.util.*

data class MusicResponse(
    @SerializedName("trackId") val trackId: Int? = 0,
    @SerializedName("artworkUrl100") val artworkUrl100: String? = "",
    @SerializedName("collectionName") val collectionName: String? = "",
    @SerializedName("artistName") val artistName: String? = "",
    @SerializedName("trackName") val trackName: String? = "",
    @SerializedName("previewUrl") val previewUrl: String? = "",
    @SerializedName("releaseDate") val releaseDate: String? = "",
    @SerializedName("primaryGenreName") val primaryGenreName: String? = "",
    @SerializedName("trackTimeMillis") val trackTimeMillis: Int? = 0,
    @SerializedName("collectionId") val collectionId: Int? = 0,
    @SerializedName("wrapperType") val wrapperType: String? = ""
) {
    fun toMusic(): Music {
        return Music(
            trackId = trackId ?: 0,
            artworkUrl100 = artworkUrl100 ?: "",
            collectionName = collectionName ?: "",
            artistName = artistName ?: "",
            trackName = trackName ?: "",
            previewUrl = previewUrl ?: "",
            releaseDate = releaseDate?.toDate() ?: Calendar.getInstance().time,
            primaryGenreName = primaryGenreName ?: "",
            trackTimeMillis = trackTimeMillis ?: 0,
            collectionId = collectionId ?: 0
        )
    }

    companion object {
        fun toMusicList(musics: List<MusicResponse>): List<Music> {
            val response = arrayListOf<Music>()
            for (item in musics) {
                if (item.wrapperType == "track")
                    response.add(item.toMusic())
            }
            return response
        }
    }
}