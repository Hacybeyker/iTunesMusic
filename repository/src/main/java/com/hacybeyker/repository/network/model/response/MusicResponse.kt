package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName
import com.hacybeyker.entities.Music

data class MusicResponse(
    @SerializedName("artworkUrl100") val artworkUrl100: String? = "",
    @SerializedName("collectionName") val collectionName: String? = "",
    @SerializedName("artistName") val artistName: String? = ""
) {
    fun toMusic(): Music {
        return Music(
            artworkUrl100 = artworkUrl100,
            collectionName = collectionName,
            artistName = artistName
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