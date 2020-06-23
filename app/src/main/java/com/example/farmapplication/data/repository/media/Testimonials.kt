package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Testimonials(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime_type")
    val mimeType: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("width")
    val width: Int
)