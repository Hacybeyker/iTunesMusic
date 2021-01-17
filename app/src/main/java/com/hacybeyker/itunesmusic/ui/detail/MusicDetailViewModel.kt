package com.hacybeyker.itunesmusic.ui.detail

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.hacybeyker.entities.Music
import com.hacybeyker.usecases.usecase.MusicUseCase
import kotlinx.coroutines.Dispatchers
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicDetailViewModel : ViewModel(), KoinComponent {

    private val musicUseCase: MusicUseCase by inject()

    fun fetchFetchMusicByAlbum(album: Int): LiveData<List<Music>> =
        liveData(Dispatchers.IO) {
            try {
                val response = musicUseCase.fetchMusicByAlbum(album)
                emit(response)
            } catch (e: Exception) {
                Log.d("TAG", "Here - fetchFetchMusicByAlbum: ${e.message}")
            }
        }
}