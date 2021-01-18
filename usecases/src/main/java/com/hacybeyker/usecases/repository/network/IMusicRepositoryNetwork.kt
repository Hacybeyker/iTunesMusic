package com.hacybeyker.usecases.repository.network

import androidx.lifecycle.LiveData
import androidx.paging.PagingData
import com.hacybeyker.entities.Music

interface IMusicRepositoryNetwork {
    suspend fun fetchMusic(term: String, limit: Int, page: Int): List<Music>
    suspend fun fetchMusicByAlbum(album: Int): List<Music>
    fun fetchMusicPaging(term: String): LiveData<PagingData<Music>>
}