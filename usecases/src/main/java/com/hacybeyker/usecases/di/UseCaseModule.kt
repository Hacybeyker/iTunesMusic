package com.hacybeyker.usecases.di

import com.hacybeyker.usecases.usecase.MusicUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single { MusicUseCase() }
}