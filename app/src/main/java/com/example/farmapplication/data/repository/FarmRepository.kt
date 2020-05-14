package com.example.farmapplication.data.repository

import com.example.farmapplication.data.remote.ApiHelper

class FarmRepository (private val apiHelper: ApiHelper){
    suspend fun getFarm() = apiHelper.getFarm()

    suspend fun postFarm() = apiHelper.postFarm()
}