package com.vivek.quicknews.data.repository

import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.network.ApiResult
import kotlinx.coroutines.flow.Flow

interface NewsRepositoryImpl {
    suspend fun searchNews(searchKey: String): Flow<ApiResult<NewsResponse>>
}