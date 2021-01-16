package com.hacybeyker.usecases.repository.network

import com.hacybeyker.entities.Music

interface IMusicRepositoryNetwork {
    suspend fun fetchMusic(): List<Music>
}