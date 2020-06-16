package com.example.quantsapp.data.network.api

import com.example.quantsapp.models.Data
import retrofit2.Response
import retrofit2.http.GET

interface QuantsApi {

    @GET("/feed/feed.json")
    suspend fun fetchFeed(): Response<Data>
}