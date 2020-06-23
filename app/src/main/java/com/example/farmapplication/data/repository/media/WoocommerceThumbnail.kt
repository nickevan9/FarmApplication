package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class WoocommerceThumbnail(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("mime_type")
    val mimeType: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("uncropped")
    val uncropped: Boolean,
    @SerializedName("width")
    val width: Int
)