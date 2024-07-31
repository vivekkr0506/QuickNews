package com.vivek.quicknews.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.vivek.quicknews.data.model.categories
import com.vivek.quicknews.ui.components.NewsSearchBar
import com.vivek.quicknews.ui.components.TrendingItem
import com.vivek.quicknews.ui.screens.viewModel.NewsViewModel
import com.vivek.quicknews.ui.theme.QuickNewsTheme
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview


@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun HomeScreen(navController: NavController) {
    val newsViewModel = hiltViewModel<NewsViewModel>()
    QuickNewsTheme()
    {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.primary
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
                NewsSearchBar(newsViewModel)
                Spacer(modifier = Modifier.height(16.dp))
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(categories.size) { index ->
                        TrendingItem(category = categories[index]){newsViewModel.searchApi(
                            categories[index].name)}
                    }
                }

                MainScreen("")
            }

        }
    }
}


