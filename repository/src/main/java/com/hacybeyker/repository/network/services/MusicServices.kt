package com.hacybeyker.repository.network.services

import com.hacybeyker.repository.network.model.response.BaseResponse
import com.hacybeyker.repository.network.model.response.MusicResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MusicServices {
    @GET("search")
    suspend fun fetchMusic(
        @Query("term") term: String,
        @Query("mediaType") mediaType: String = "music",
        @Query("limit") limit: Int,
        @Query("page") page: Int,
        @Query("entity") song: String = "song"
    ): Response<BaseResponse<List<MusicResponse>>>

    @GET("lookup")
    suspend fun fetchMusicByAlbum(
        @Query("id") id: Int,
        @Query("entity") song: String = "song"
    ): Response<BaseResponse<List<MusicResponse>>>
}