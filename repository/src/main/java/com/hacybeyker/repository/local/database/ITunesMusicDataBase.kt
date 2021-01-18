package com.hacybeyker.repository.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.hacybeyker.repository.local.database.dao.MusicDao
import com.hacybeyker.repository.local.database.dao.SuggestionDao
import com.hacybeyker.repository.local.database.model.MusicModel
import com.hacybeyker.repository.local.database.model.SuggestionModel

@Database(
    entities = [MusicModel::class, SuggestionModel::class],
    version = 1,
    exportSchema = true
)
@TypeConverters(TypeConverts::class)
abstract class ITunesMusicDataBase : RoomDatabase() {
    abstract val musicDao: MusicDao
    abstract val suggestionDao: SuggestionDao
}
