package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Cury(
    @SerializedName("href")
    val href: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("templated")
    val templated: Boolean
)