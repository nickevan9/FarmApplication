package com.example.farmapplication.ui.splash

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.farmapplication.data.repository.FarmRepository
import com.example.farmapplication.utils.Resource
import kotlinx.coroutines.Dispatchers

class SplashViewModel (private val farmRepository: FarmRepository) : ViewModel() {
    fun getFarm() = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = farmRepository.getFarm()))
        }catch (e : Exception){
            emit(Resource.error(data = null, message = e.message ?: "Error Occurred!"))
        }
    }
}