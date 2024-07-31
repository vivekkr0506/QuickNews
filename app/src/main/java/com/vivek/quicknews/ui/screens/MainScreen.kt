package com.vivek.quicknews.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.network.ApiResult
import com.vivek.quicknews.ui.components.ArticleCard
import com.vivek.quicknews.ui.screens.viewModel.NewsViewModel
import java.lang.reflect.Modifier

@Composable
fun MainScreen(searchKey : String) {
    val newsViewModel = hiltViewModel<NewsViewModel>()
    val isLoading by newsViewModel.isLoading.collectAsState()
    val topHeadline by newsViewModel.topHeadline.collectAsState()

    val articles = newsViewModel.getBreakingNews().collectAsLazyPagingItems()
    LaunchedEffect(key1 = 0) {
        newsViewModel.topHeadline()
    }
    if(newsViewModel.searchData.value != null){
        newsViewModel.searchData.value?.let {
            if (it is ApiResult.Success<NewsResponse>) {
                ArticleCard(it.data)
            }
        }
    }else{
        topHeadline?.let {
           ArticleCard(data = it)
        }
    }
}


