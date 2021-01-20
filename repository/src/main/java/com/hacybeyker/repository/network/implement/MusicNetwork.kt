package com.hacybeyker.repository.network.implement

import androidx.lifecycle.LiveData
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.liveData
import com.hacybeyker.entities.Music
import com.hacybeyker.repository.network.exception.EmptyError
import com.hacybeyker.repository.network.exception.GenericException
import com.hacybeyker.repository.network.exception.NetworkException
import com.hacybeyker.repository.network.model.response.MusicResponse
import com.hacybeyker.repository.network.services.MusicServices
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicNetwork : IMusicRepositoryNetwork, KoinComponent {

    private val musicServices: MusicServices by inject()

    override suspend fun fetchMusic(term: String, limit: Int, page: Int): List<Music> {
        return try {
            val response = musicServices.fetchMusic(term = term, limit = limit, page = page)
            var musics: ArrayList<Music>? = null
            if (response.isSuccessful) {
                response.body()?.apply {
                    if (this.results.isEmpty()) throw EmptyError()
                    musics = arrayListOf()
                    musics?.addAll(MusicResponse.toMusicList(this.results))
                }
            } else throw NetworkException()
            musics ?: throw GenericException()
        } catch (e: Exception) {
            throw e
        }
    }

    override suspend fun fetchMusicByAlbum(album: Int): List<Music> {
        return try {
            val response = musicServices.fetchMusicByAlbum(id = album)
            var musics: ArrayList<Music>? = null
            if (response.isSuccessful) {
                response.body()?.apply {
                    if (this.results.isEmpty()) throw EmptyError()
                    musics = arrayListOf()
                    musics?.addAll(MusicResponse.toMusicList(this.results))
                }
            } else throw NetworkException()
            musics ?: throw GenericException()
        } catch (e: Exception) {
            throw e
        }
    }

    override fun fetchMusicPaging(term: String): LiveData<PagingData<Music>> {
        try {
            val response = Pager(
                config = PagingConfig(
                    pageSize = 20,
                    maxSize = 200,
                    enablePlaceholders = false
                ),
                pagingSourceFactory = { MusicPagingSource(term = term) }).liveData
            return response ?: throw GenericException()
        } catch (e: Exception) {
            throw GenericException()
        }
    }
}