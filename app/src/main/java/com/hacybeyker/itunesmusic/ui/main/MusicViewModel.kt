package com.hacybeyker.itunesmusic.ui.main

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacybeyker.entities.Music
import com.hacybeyker.repository.network.exception.GenericException
import com.hacybeyker.usecases.usecase.MusicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicViewModel : ViewModel(), KoinComponent {

    private val musicUseCase: MusicUseCase by inject()
    val musicSuccess = MutableLiveData<List<Music>>()

    val fetchMusic = viewModelScope.launch(Dispatchers.IO) {
        try {
            val musics = musicUseCase.fetchMusic()
            musicSuccess.postValue(musics)
        } catch (ex: Exception) {
            when (ex) {
                is GenericException -> {
                    Log.d("TAG", "Here - Generic: ${ex.code} - ${ex.title}")
                }
                else -> {
                    Log.d("TAG", "Here - Exception: ${ex.message} ")
                }
            }
        }
    }
}