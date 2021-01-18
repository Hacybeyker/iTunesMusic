package com.hacybeyker.usecases.repository.local

import com.hacybeyker.entities.Music

interface IMusicRepositoryDataBase {
    suspend fun saveMusic(music: Music)
    suspend fun fetchMusic(): List<Music>
}