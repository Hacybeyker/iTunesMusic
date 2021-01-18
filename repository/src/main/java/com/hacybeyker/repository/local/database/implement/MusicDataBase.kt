package com.hacybeyker.repository.local.database.implement

import com.hacybeyker.entities.Music
import com.hacybeyker.repository.local.database.dao.MusicDao
import com.hacybeyker.repository.local.database.model.MusicModel
import com.hacybeyker.usecases.repository.local.IMusicRepositoryDataBase
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicDataBase : IMusicRepositoryDataBase, KoinComponent {

    private val musicDao: MusicDao by inject()

    override suspend fun saveMusic(music: Music) {
        musicDao.insert(
            MusicModel(
                trackId = music.trackId,
                artworkUrl100 = music.artworkUrl100,
                collectionName = music.collectionName,
                artistName = music.artistName,
                trackName = music.trackName,
                previewUrl = music.previewUrl,
                releaseDate = music.releaseDate,
                primaryGenreName = music.primaryGenreName,
                trackTimeMillis = music.trackTimeMillis,
                collectionId = music.collectionId
            )
        )
    }

    override suspend fun fetchMusic(): List<Music> {
        return MusicModel.toMusicList(musicDao.fetchMusic())
    }
}
