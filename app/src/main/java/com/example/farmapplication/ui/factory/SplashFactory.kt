package com.example.farmapplication.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmapplication.data.remote.ApiHelper
import com.example.farmapplication.data.repository.FarmRepository
import com.example.farmapplication.ui.splash.SplashViewModel

class SplashFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SplashViewModel::class.java)) {
            return SplashViewModel(FarmRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}