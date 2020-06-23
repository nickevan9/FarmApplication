package com.example.farmapplication.data.remote

import com.example.farmapplication.data.model.LoginResponseEntity
import com.example.farmapplication.data.repository.media.MediaRepository
import okhttp3.Call
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.http.*


interface ApiService {
    @GET("/AppLogin.aspx")
    suspend fun loginUser(
        @Query("taikhoan") username: String,
        @Query("matkhau") password: String
    ): LoginResponseEntity


    @Multipart
    @POST("retrofit_example/upload_image.php")
    suspend fun uploadFile(
        @Part file: MultipartBody.Part?,
        @Part("title") title: RequestBody?,
        @Part("caption") caption: RequestBody?,
        @Part("description") description: RequestBody?

    ): MediaRepository

}