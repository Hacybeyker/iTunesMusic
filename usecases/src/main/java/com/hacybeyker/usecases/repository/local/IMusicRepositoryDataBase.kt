package com.hacybeyker.usecases.repository.local

import com.hacybeyker.entities.Music

interface IMusicRepositoryDataBase {
    suspend fun saveMusic(term: String, music: Music)
    suspend fun fetchMusic(term: String): List<Music>
}