package com.hacybeyker.itunesmusic.di

import com.hacybeyker.itunesmusic.ui.main.MusicViewModel
import com.hacybeyker.itunesmusic.ui.main.SuggestionViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { MusicViewModel() }
    viewModel { SuggestionViewModel() }
}