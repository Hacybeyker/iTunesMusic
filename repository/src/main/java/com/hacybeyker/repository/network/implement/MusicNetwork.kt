package com.hacybeyker.repository.network.implement

import com.hacybeyker.entities.Music
import com.hacybeyker.repository.network.exception.GenericException
import com.hacybeyker.repository.network.model.response.MusicResponse
import com.hacybeyker.repository.network.services.MusicServices
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.lang.Exception

class MusicNetwork : IMusicRepositoryNetwork, KoinComponent {

    private val musicServices: MusicServices by inject()

    override suspend fun fetchMusic(): List<Music> {
        val response = musicServices.fetchMusic()
        val musics = arrayListOf<Music>()
        try {
            if (response.isSuccessful) {
                response.body()?.apply {
                    musics.addAll(MusicResponse.toMusicList(this.results))
                }
            }
            return musics
        } catch (e: Exception) {
            throw GenericException()
        }
    }
}