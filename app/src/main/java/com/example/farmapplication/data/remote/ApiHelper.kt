package com.example.farmapplication.data.remote

class ApiHelper(private val apiService: ApiService) {
    suspend fun loginUser(username: String, password: String) =
        apiService.loginUser(username, password)

    suspend fun getFarm() = null

    suspend fun postFarm() = null
}