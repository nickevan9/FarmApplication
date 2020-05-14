package com.example.farmapplication.data.remote

import com.example.farmapplication.data.model.LoginResponseEntity
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/AppLogin.aspx")
    suspend fun loginUser(
        @Query("taikhoan") username: String,
        @Query("matkhau") password: String
    ): LoginResponseEntity

}