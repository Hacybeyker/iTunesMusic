package com.hacybeyker.usecases.usecase

import com.hacybeyker.entities.Music
import com.hacybeyker.usecases.repository.local.IMusicRepositoryDataBase
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicUseCase : KoinComponent {
    private val iMusicRepositoryNetwork: IMusicRepositoryNetwork by inject()
    private val iMusicRepositoryDataBase: IMusicRepositoryDataBase by inject()

    suspend fun fetchMusic(term: String, limit: Int, page: Int): List<Music> {
        val musicList = iMusicRepositoryNetwork.fetchMusic(term = term, limit = limit, page = page)
        musicList.forEach { iMusicRepositoryDataBase.saveMusic(it) }
        return musicList
    }

    suspend fun fetchMusicByAlbum(album: Int): List<Music> {
        return iMusicRepositoryNetwork.fetchMusicByAlbum(album)
    }
}