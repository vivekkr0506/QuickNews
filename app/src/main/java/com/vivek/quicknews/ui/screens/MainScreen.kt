package com.vivek.quicknews.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.collectAsLazyPagingItems
import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.network.ApiResult
import com.vivek.quicknews.ui.components.ArticleCard
import com.vivek.quicknews.ui.screens.viewModel.NewsViewModel

@Composable
fun MainScreen(searchKey: String, navController: NavController) {
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
                ArticleCard(it.data,navController)
            }
        }
    }else{
        topHeadline?.let {
           ArticleCard(data = it, navController = navController)
        }
    }
}


