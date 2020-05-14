package com.example.farmapplication.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.farmapplication.data.repository.UserRepository
import com.example.farmapplication.utils.Resource
import kotlinx.coroutines.Dispatchers

class LoginViewModel (private val userRepository: UserRepository) : ViewModel() {
    fun loginUser(username: String, password: String) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = userRepository.loginUser(username, password)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}