package com.example.farmapplication.ui.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.farmapplication.data.remote.ApiHelper
import com.example.farmapplication.data.repository.UserRepository
import com.example.farmapplication.ui.login.LoginViewModel

class LoginFactory (private val apiHelper: ApiHelper) : ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(UserRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}