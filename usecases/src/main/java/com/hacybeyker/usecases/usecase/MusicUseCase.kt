package com.hacybeyker.usecases.usecase

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.hacybeyker.entities.Music
import com.hacybeyker.usecases.repository.local.IMusicRepositoryDataBase
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import com.hacybeyker.usecases.util.isAirplaneModeActive
import com.hacybeyker.usecases.util.isConnected
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicUseCase : KoinComponent {
    private val context: Context by inject()
    private val iMusicRepositoryNetwork: IMusicRepositoryNetwork by inject()
    private val iMusicRepositoryDataBase: IMusicRepositoryDataBase by inject()

    suspend fun fetchMusic(term: String, limit: Int, page: Int): List<Music> {
        val musicList = arrayListOf<Music>()
        if (!context.isConnected() || context.isAirplaneModeActive()) {
            musicList.addAll(iMusicRepositoryDataBase.fetchMusic(term = term))
        } else {
            musicList.addAll(
                iMusicRepositoryNetwork.fetchMusic(
                    term = term,
                    limit = limit,
                    page = page
                )
            )
            musicList.forEach { iMusicRepositoryDataBase.saveMusic(term, it) }
        }
        return musicList
    }

    suspend fun fetchMusicByAlbum(album: Int): List<Music> {
        val albumList = arrayListOf<Music>()
        if (context.isConnected() || !context.isAirplaneModeActive()) {
            albumList.addAll(iMusicRepositoryNetwork.fetchMusicByAlbum(album))
        }
        return albumList
    }

    fun fetchMusicPaging(term: String): LiveData<PagingData<Music>> {
        return iMusicRepositoryNetwork.fetchMusicPaging(term = term)
    }
}