package com.example.farmapplication.data.remote

import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {

    private const val BASE_URL = "http://172.19.200.197:8001/"
    private const val USERNAME = "hoanganh"
    private const val PASSWORD = "lehoanganh"

    private fun getRetrofit(): Retrofit {
        val client = OkHttpClient.Builder()
            .addInterceptor(BasicAuthInterceptor(USERNAME, PASSWORD))
            .build()
        val gson = GsonBuilder()
            .setLenient()
            .create();

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build() //Doesn't require the adapter
    }

    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}