package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class MediaRepository(
    @SerializedName("alt_text")
    val altText: String,
    @SerializedName("author")
    val author: Int,
    @SerializedName("caption")
    val caption: Caption,
    @SerializedName("comment_status")
    val commentStatus: String,
    @SerializedName("date")
    val date: String,
    @SerializedName("date_gmt")
    val dateGmt: String,
    @SerializedName("description")
    val description: Description,
    @SerializedName("generated_slug")
    val generatedSlug: String,
    @SerializedName("guid")
    val guid: Guid,
    @SerializedName("id")
    val id: Int,
    @SerializedName("link")
    val link: String,
    @SerializedName("_links")
    val links: Links,
    @SerializedName("media_details")
    val mediaDetails: MediaDetails,
    @SerializedName("media_type")
    val mediaType: String,
    @SerializedName("meta")
    val meta: List<Any>,
    @SerializedName("mime_type")
    val mimeType: String,
    @SerializedName("modified")
    val modified: String,
    @SerializedName("modified_gmt")
    val modifiedGmt: String,
    @SerializedName("permalink_template")
    val permalinkTemplate: String,
    @SerializedName("ping_status")
    val pingStatus: String,
    @SerializedName("post")
    val post: Any,
    @SerializedName("slug")
    val slug: String,
    @SerializedName("source_url")
    val sourceUrl: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("template")
    val template: String,
    @SerializedName("title")
    val title: Title,
    @SerializedName("type")
    val type: String
)