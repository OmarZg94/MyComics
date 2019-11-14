package com.ozuniga.mycomics.net

import com.google.gson.annotations.SerializedName

open class MarvelGenericResult {
    @SerializedName("code")
    open var code: Int? = null
    @SerializedName("status")
    open var status: String? = null
    @SerializedName("copyright")
    open var copyright: String? = null
    @SerializedName("attributionText")
    open var attributionText: String? = null
    @SerializedName("attributionHTML")
    open var attributionHTML: String? = null
    @SerializedName("etag")
    open var etag: String? = null
}

class ComicsResult(@SerializedName("data") val data: ComicsObject) : MarvelGenericResult()

class ComicsObject(@SerializedName("offset") val offset: Int,
                   @SerializedName("limit") val limit: Int,
                   @SerializedName("total") val total: Long,
                   @SerializedName("count") val count: Int,
                   @SerializedName("results") val results: MutableList<ComicData>)

class ComicData(@SerializedName("id") val id: Long,
                @SerializedName("digitalId") val digitalId: Long,
                @SerializedName("title") val title: String,
                @SerializedName("variantDescription") val variantDescription: String,
                @SerializedName("description") val description: String,
                @SerializedName("thumbnail") val thumbnail: ImageResult)

class ImageResult(@SerializedName("path") val path: String,
                  @SerializedName("extension") val extension: String)