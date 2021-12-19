package com.example.mytestapp.data.remote.home


interface HomeRepository {
    suspend fun getData(): Int
}