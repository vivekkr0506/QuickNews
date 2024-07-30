package com.vivek.quicknews.data.model

import com.google.gson.annotations.SerializedName

data class BaseModel(
    @SerializedName("page")
    val page: Int,
    @SerializedName("results")
    val results: List<Article>,
    @SerializedName("total_pages")
    val totalPages: Int,
    @SerializedName("total_results")
    val totalResults: Int
)
data class NewsResponse(
    @SerializedName("articles") val articles: List<Article>,
    @SerializedName("status") val status: String,
    @SerializedName("totalResults") val totalResults: Int,

    )

data class Article(
    @SerializedName("author") val author: String,
    @SerializedName("content") val content: String,
    @SerializedName("description") val description: String,
    @SerializedName("publishedAt") val publishedAt: String,
    @SerializedName("source") val source: Source,
    @SerializedName("title") val title: String,
    @SerializedName("urlToImage") val urlToImage: String
)


data class Source(
    @SerializedName("id") val id: String, @SerializedName("name") val name: String
)
