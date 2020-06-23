package com.example.farmapplication.data.repository.media


import com.google.gson.annotations.SerializedName

data class Sizes(
    @SerializedName("blog-navi")
    val blogNavi: BlogNavi,
    @SerializedName("clients-slider")
    val clientsSlider: ClientsSlider,
    @SerializedName("full")
    val full: Full,
    @SerializedName("medium")
    val medium: Medium,
    @SerializedName("portfolio-mf-w")
    val portfolioMfW: PortfolioMfW,
    @SerializedName("post-thumbnail")
    val postThumbnail: PostThumbnail,
    @SerializedName("shop_catalog")
    val shopCatalog: ShopCatalog,
    @SerializedName("shop_single")
    val shopSingle: ShopSingle,
    @SerializedName("shop_thumbnail")
    val shopThumbnail: ShopThumbnail,
    @SerializedName("testimonials")
    val testimonials: Testimonials,
    @SerializedName("thumbnail")
    val thumbnail: Thumbnail,
    @SerializedName("woocommerce_gallery_thumbnail")
    val woocommerceGalleryThumbnail: WoocommerceGalleryThumbnail,
    @SerializedName("woocommerce_single")
    val woocommerceSingle: WoocommerceSingle,
    @SerializedName("woocommerce_thumbnail")
    val woocommerceThumbnail: WoocommerceThumbnail,
    @SerializedName("50x50")
    val x50: X50
)