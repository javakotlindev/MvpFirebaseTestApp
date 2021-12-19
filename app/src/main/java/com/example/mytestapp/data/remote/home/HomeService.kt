package com.example.mytestapp.data.remote.home

import com.example.mytestapp.common.Constants
import retrofit2.Response
import retrofit2.http.GET

interface HomeService {
    @GET("/users")
    suspend fun getData(): Response<Void>
}