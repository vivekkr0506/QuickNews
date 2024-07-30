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
        @Query("apiKey") api_key: String = "33523e1cf179484089bfa8d6df00988f"
    ): NewsResponse

    @GET("movie/upcoming")
    suspend fun paginatedArticles(
        @Query("page") page: Int,
        @Query("query") searchKey: String?,
        @Query("apiKey") api_key: String = "33523e1cf179484089bfa8d6df00988f"
    ): BaseModel

}