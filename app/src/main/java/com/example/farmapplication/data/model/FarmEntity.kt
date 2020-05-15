package com.example.farmapplication.data.model

import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class FarmEntity(
    @Id
    @SerializedName("")
    var id: Long = 0,

    @SerializedName("")
    val name: String,

    @SerializedName("")
    val imagePath: String,

    @SerializedName("")
    val description: String,

    @SerializedName("")
    val region: Int

)