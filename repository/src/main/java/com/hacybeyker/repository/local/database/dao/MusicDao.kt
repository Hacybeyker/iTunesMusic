package com.hacybeyker.repository.local.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.hacybeyker.repository.local.database.model.MusicModel

@Dao
interface MusicDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(musicModel: MusicModel)

    @Query("SELECT * FROM music WHERE artistName LIKE :term OR term LIKE :term")
    suspend fun fetchMusic(term: String): List<MusicModel>
}