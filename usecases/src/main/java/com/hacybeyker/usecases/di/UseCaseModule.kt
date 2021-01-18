package com.hacybeyker.usecases.di

import com.hacybeyker.usecases.usecase.MusicUseCase
import com.hacybeyker.usecases.usecase.SuggestionUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MusicUseCase() }
    single { SuggestionUseCase() }
}