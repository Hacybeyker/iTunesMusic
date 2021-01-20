package com.hacybeyker.itunesmusic.ui.main.viewmodel

import androidx.databinding.ObservableField
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacybeyker.entities.Music
import com.hacybeyker.usecases.usecase.MusicUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class MusicViewModel : ViewModel(), KoinComponent {

    private val musicUseCase: MusicUseCase by inject()

    private val currentTermMutableLiveData = ObservableField<String>()

    private val musicMutableLiveData = MutableLiveData<List<Music>>()
    val musicLiveData: LiveData<List<Music>> get() = musicMutableLiveData

    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = errorMutableLiveData

    private fun fetchMusic() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response =
                    musicUseCase.fetchMusic(currentTermMutableLiveData.get().toString(), 20, 1)
                musicMutableLiveData.postValue(response)
            } catch (e: Exception) {
                errorMutableLiveData.postValue(e)
            }
        }
    }

    fun searchMusic(term: String) {
        currentTermMutableLiveData.set(term)
        fetchMusic()
    }
}