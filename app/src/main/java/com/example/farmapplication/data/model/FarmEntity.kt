package com.example.farmapplication.data.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity

@Entity
data class FarmEntity(
    @SerializedName("")
    val id: Int,

    @SerializedName("")
    val name: String,

    @SerializedName("")
    val imagePath: String,

    @SerializedName("")
    val description: String
)