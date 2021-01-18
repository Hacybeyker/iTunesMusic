package com.hacybeyker.usecases.usecase

import com.hacybeyker.usecases.repository.local.ISuggestionRepositoryDataBase
import org.koin.core.KoinComponent
import org.koin.core.inject

class SuggestionUseCase : KoinComponent {

    private val iSuggestionRepositoryDataBase: ISuggestionRepositoryDataBase by inject()

    suspend fun saveSuggestion(term: String): List<String> {
        iSuggestionRepositoryDataBase.saveSuggestion(term = term)
        return fetchSuggestion()
    }

    suspend fun fetchSuggestion(): List<String> {
        return iSuggestionRepositoryDataBase.fetchSuggestion()
    }
}