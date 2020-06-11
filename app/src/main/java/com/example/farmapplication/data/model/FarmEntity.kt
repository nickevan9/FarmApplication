package com.example.farmapplication.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id
import kotlinx.android.parcel.Parcelize

@Entity
@Parcelize
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

): Parcelable