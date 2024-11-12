package com.example.e_comm.data.remotedata

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("Banner.json")
    suspend fun getBanners(): List<RemoteSliderModel>

    @GET("Items.json")
    suspend fun getData(): List<RemoteItems>

    @GET("Items.json?orderBy=\"showRecommended\"")
    suspend fun getRecommendedItems(
        @Query("equalTo") showRecommended: Boolean = true
    ): Map<Int,RemoteItems>
}