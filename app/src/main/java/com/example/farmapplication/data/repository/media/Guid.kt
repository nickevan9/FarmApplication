package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Guid(
    @SerializedName("raw")
    val raw: String,
    @SerializedName("rendered")
    val rendered: String
)