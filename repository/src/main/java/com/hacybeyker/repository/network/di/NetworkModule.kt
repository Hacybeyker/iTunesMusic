package com.hacybeyker.repository.network.di

import com.hacybeyker.repository.network.implement.MusicNetwork
import com.hacybeyker.usecases.repository.network.IMusicRepositoryNetwork
import org.koin.dsl.module

val networkModule = module {
    single<IMusicRepositoryNetwork> { MusicNetwork() }
}