package com.hacybeyker.repository.local.database.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hacybeyker.entities.Music
import java.util.*

@Entity(tableName = "music")
data class MusicModel(
    @PrimaryKey
    val trackId: Int,
    val artworkUrl100: String,
    val collectionName: String,
    val artistName: String,
    val trackName: String,
    val previewUrl: String,
    val releaseDate: Date,
    val primaryGenreName: String,
    val trackTimeMillis: Int,
    val collectionId: Int
) {
    fun toMusic(): Music {
        return Music(
            trackId = trackId,
            artworkUrl100 = artworkUrl100,
            collectionName = collectionName,
            artistName = artistName,
            trackName = trackName,
            previewUrl = previewUrl,
            releaseDate = releaseDate,
            primaryGenreName = primaryGenreName,
            trackTimeMillis = trackTimeMillis,
            collectionId = collectionId
        )
    }

    companion object {
        fun toMusicList(musics: List<MusicModel>): List<Music> {
            val response = arrayListOf<Music>()
            for (item in musics)
                response.add(item.toMusic())
            return response
        }
    }
}