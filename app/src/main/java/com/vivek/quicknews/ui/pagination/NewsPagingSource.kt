package com.vivek.quicknews.ui.pagination

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.vivek.quicknews.data.model.Article
import com.vivek.quicknews.data.remote.NewsApiService
class NewsPagingSource(
    private val newsApiService: NewsApiService,
): PagingSource<Int, Article>() {
    override fun getRefreshKey(state: PagingState<Int, Article>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Article> {
        return try {
            Log.e("Vivek","HERE_____")
            val page = params.key ?: 1
            val response = newsApiService.getTopHeadline(page = page)

            LoadResult.Page(
                data = response.articles,
                prevKey = if (page == 1) null else page.minus(1),
                nextKey = if (response.articles.isEmpty()) null else page.plus(1),
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }
}