package com.vivek.quicknews.data.remote

import androidx.multidex.BuildConfig
import com.vivek.quicknews.data.model.BaseModel
import com.vivek.quicknews.data.model.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsApiService {
    @GET("everything/")
    suspend fun search(
        @Query("q") searchKey: String,
        @Query("apiKey") api_key: String = "d78c8edc8b4c4f26a1c2ea75e253d3b3"
    ): NewsResponse


    @GET("top-headlines")
    suspend fun getTopHeadline(
        @Query("page") page: Int,
        @Query("country") country: String = "us",
        @Query("category") category: String = "business",
        @Query("apiKey") api_key: String = "d78c8edc8b4c4f26a1c2ea75e253d3b3"

    ) : NewsResponse
}