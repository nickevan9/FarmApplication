package com.example.farmapplication.data.repository

import com.example.farmapplication.data.remote.ApiHelper

class UserRepository(private val apiHelper: ApiHelper) {
    suspend fun loginUser(username: String, password: String) =
        apiHelper.loginUser(username, password)


}