package com.hacybeyker.usecases.repository.local

interface ISuggestionRepositoryDataBase {
    suspend fun saveSuggestion(term: String)
    suspend fun fetchSuggestion(): List<String>
}