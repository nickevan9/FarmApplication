package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Links(
    @SerializedName("about")
    val about: List<About>,
    @SerializedName("author")
    val author: List<Author>,
    @SerializedName("collection")
    val collection: List<Collection>,
    @SerializedName("curies")
    val curies: List<Cury>,
    @SerializedName("replies")
    val replies: List<Reply>,
    @SerializedName("self")
    val self: List<Self>,
    @SerializedName("wp:action-assign-author")
    val wpActionAssignAuthor: List<WpActionAssignAuthor>,
    @SerializedName("wp:action-unfiltered-html")
    val wpActionUnfilteredHtml: List<WpActionUnfilteredHtml>
)