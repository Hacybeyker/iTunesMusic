package com.hacybeyker.itunesmusic.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hacybeyker.usecases.usecase.SuggestionUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.KoinComponent
import org.koin.core.inject

class SuggestionViewModel : ViewModel(), KoinComponent {

    private val suggestionUseCase: SuggestionUseCase by inject()

    private val suggestionMutableLiveData: MutableLiveData<List<String>> by lazy { MutableLiveData<List<String>>() }
    val suggestionLiveData: LiveData<List<String>> get() = suggestionMutableLiveData
    private val errorMutableLiveData = MutableLiveData<Throwable>()
    val errorLiveData: LiveData<Throwable> get() = errorMutableLiveData

    fun fetchSuggestion() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = suggestionUseCase.fetchSuggestion()
                suggestionMutableLiveData.postValue(response)
            } catch (e: Exception) {
                errorMutableLiveData.postValue(e)
            }
        }
    }

    fun saveSuggestion(term: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = suggestionUseCase.saveSuggestion(term = term)
                suggestionMutableLiveData.postValue(response)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}