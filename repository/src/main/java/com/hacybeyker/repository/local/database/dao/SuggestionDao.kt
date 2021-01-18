package com.hacybeyker.repository.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hacybeyker.repository.local.database.model.SuggestionModel

@Dao
interface SuggestionDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(suggestionModel: SuggestionModel)

    @Query("SELECT * FROM suggestion")
    suspend fun fetchSuggestion(): List<SuggestionModel>
}