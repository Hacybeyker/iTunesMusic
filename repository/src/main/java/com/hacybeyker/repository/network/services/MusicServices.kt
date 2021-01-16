package com.hacybeyker.repository.network.services

import com.hacybeyker.repository.network.model.response.BaseResponse
import com.hacybeyker.repository.network.model.response.MusicResponse
import retrofit2.Response
import retrofit2.http.GET

interface MusicServices {
    @GET("search?term=mana&mediaType=music&limit=20&page=1")
    suspend fun fetchMusic(): Response<BaseResponse<List<MusicResponse>>>
}