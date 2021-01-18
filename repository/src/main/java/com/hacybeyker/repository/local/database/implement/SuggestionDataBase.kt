package com.hacybeyker.repository.local.database.implement

import com.hacybeyker.repository.local.database.dao.SuggestionDao
import com.hacybeyker.repository.local.database.model.SuggestionModel
import com.hacybeyker.usecases.repository.local.ISuggestionRepositoryDataBase
import org.koin.core.KoinComponent
import org.koin.core.inject

class SuggestionDataBase : ISuggestionRepositoryDataBase, KoinComponent {

    private val suggestionDao: SuggestionDao by inject()

    override suspend fun saveSuggestion(term: String) {
        suggestionDao.insert(SuggestionModel(term = term))
    }

    override suspend fun fetchSuggestion(): List<String> {
        return SuggestionModel.toString(suggestionDao.fetchSuggestion())
    }


}