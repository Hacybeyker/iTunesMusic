package com.hacybeyker.repository.local.database.di

import androidx.room.Room
import com.hacybeyker.repository.local.database.ITunesMusicDataBase
import com.hacybeyker.repository.local.database.implement.MusicDataBase
import com.hacybeyker.repository.local.database.implement.SuggestionDataBase
import com.hacybeyker.usecases.repository.local.IMusicRepositoryDataBase
import com.hacybeyker.usecases.repository.local.ISuggestionRepositoryDataBase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val dataBaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            ITunesMusicDataBase::class.java,
            "${androidContext().packageName}.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }
    single { get<ITunesMusicDataBase>().musicDao }
    single<IMusicRepositoryDataBase> { MusicDataBase() }
    single { get<ITunesMusicDataBase>().suggestionDao }
    single<ISuggestionRepositoryDataBase> { SuggestionDataBase() }
}

