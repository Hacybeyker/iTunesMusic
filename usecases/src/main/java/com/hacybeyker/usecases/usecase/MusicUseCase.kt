package com.hacybeyker.usecases.usecase

import com.hacybeyker.entities.Music
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicUseCase : KoinComponent {
    private val iMusicRepositoryNetwork: IMusicRepositoryNetwork by inject()

    suspend fun fetchMusic(term: String, limit: Int, page: Int): List<Music> {
        return iMusicRepositoryNetwork.fetchMusic(term = term, limit = limit, page = page)
    }

    suspend fun fetchMusicByAlbum(album: Int): List<Music> {
        return iMusicRepositoryNetwork.fetchMusicByAlbum(album)
    }
}