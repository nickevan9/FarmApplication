package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Reply(
    @SerializedName("embeddable")
    val embeddable: Boolean,
    @SerializedName("href")
    val href: String
)