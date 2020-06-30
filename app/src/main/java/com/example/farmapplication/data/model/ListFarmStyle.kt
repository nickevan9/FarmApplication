package com.example.farmapplication.data.model

import io.objectbox.annotation.Entity
import io.objectbox.annotation.Id

@Entity
data class ListFarmStyle(
    @Id
    var id: Long = 0,

    var listEntity: List<FarmEntity>,

    var region: Int
)