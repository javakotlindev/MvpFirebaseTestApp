package com.example.mytestapp.data.remote.home

import com.example.mytestapp.common.Constants
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeRepositoryImpl :
    HomeRepository {
    override suspend fun getData(): Int {
        val service : HomeService = Constants.getClient()!!.create(HomeService::class.java)
        val response = service.getData()
        return response.code()
    }
}