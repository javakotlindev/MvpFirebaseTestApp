package com.example.mytestapp.ui.main

import com.example.mytestapp.data.remote.home.HomeRepositoryImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomePresenter {
    lateinit var homeView: HomeView

    private val repository: HomeRepositoryImpl = HomeRepositoryImpl()
    fun attachView(homeView: HomeView) {
        this.homeView = homeView
    }

    fun checkData() {
        CoroutineScope(Dispatchers.IO).launch {
            withContext(Dispatchers.IO) {

                when (val response = repository.getData()) {
                    200 -> {
                        withContext(Dispatchers.Main) {
                            homeView.showMessage("Website is online")
                        }
                    }
                    else -> {
                        withContext(Dispatchers.Main) {
                            homeView.showMessage(response.toString())
                        }
                    }
                }
            }

        }
    }
}