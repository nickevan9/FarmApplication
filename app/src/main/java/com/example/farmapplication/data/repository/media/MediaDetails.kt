package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class MediaDetails(
    @SerializedName("file")
    val `file`: String,
    @SerializedName("height")
    val height: Int,
    @SerializedName("image_meta")
    val imageMeta: ImageMeta,
    @SerializedName("sizes")
    val sizes: Sizes,
    @SerializedName("width")
    val width: Int
)