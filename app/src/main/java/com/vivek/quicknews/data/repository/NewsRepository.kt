package com.vivek.quicknews.data.repository

import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.data.remote.NewsApiService
import com.vivek.quicknews.network.ApiResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject


class NewsRepository @Inject constructor(
    private val newsApiService: NewsApiService
) : NewsRepositoryImpl {

    override suspend fun searchNews(searchKey: String): Flow<ApiResult<NewsResponse>> = flow {
            emit(ApiResult.Loading)
            try {
                val searchResult = newsApiService.search(searchKey = searchKey)
                emit(ApiResult.Success(searchResult))

            } catch (e: Exception) {
                emit(ApiResult.Error(e))
            }
    }

    override suspend fun topHeadline(): Flow<ApiResult<NewsResponse>> = flow {
        emit(ApiResult.Loading)
        try {
            val topHeadline = newsApiService.getTopHeadline()
            emit(ApiResult.Success(topHeadline))

        } catch (e: Exception) {
            emit(ApiResult.Error(e))
        }
    }
}