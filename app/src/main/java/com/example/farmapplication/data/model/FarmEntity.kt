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
    var id: Long = 0,

    val name: String,

    val imagePath: String,

    val price: String,

    val linkUri: String

) : Parcelable