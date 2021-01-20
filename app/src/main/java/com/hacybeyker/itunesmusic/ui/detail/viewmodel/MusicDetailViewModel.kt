package com.hacybeyker.itunesmusic.ui.detail.viewmodel

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

    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = errorMutableLiveData

    fun fetchFetchMusicByAlbum(album: Int): LiveData<List<Music>> =
        liveData(Dispatchers.IO) {
            try {
                emit(musicUseCase.fetchMusicByAlbum(album))
            } catch (e: Exception) {
                errorMutableLiveData.postValue(e)
            }
        }
}