package com.example.mytestapp.common

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory

import retrofit2.Retrofit




    object Constants {
        val BASE_URL = "https://api.github.com"
        fun getClient(): Retrofit? {
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            return Retrofit.Builder().apply {
                baseUrl(BASE_URL)
                client(OkHttpClient.Builder().addNetworkInterceptor(httpLoggingInterceptor).build())
                    .addCallAdapterFactory(CoroutineCallAdapterFactory())
                addConverterFactory(GsonConverterFactory.create())
            }.build()
        }
    }

