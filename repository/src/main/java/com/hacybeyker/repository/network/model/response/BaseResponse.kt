package com.hacybeyker.repository.network.model.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("resultCount") val resultCount: String,
    @SerializedName("results") val results: T
)