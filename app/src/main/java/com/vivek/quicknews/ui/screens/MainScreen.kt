package com.vivek.quicknews.ui.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.R
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.skydoves.landscapist.ImageOptions
import com.skydoves.landscapist.animation.circular.CircularRevealPlugin
import com.skydoves.landscapist.coil.CoilImage
import com.skydoves.landscapist.components.rememberImageComponent
import com.skydoves.landscapist.placeholder.shimmer.Shimmer
import com.skydoves.landscapist.placeholder.shimmer.ShimmerPlugin
import com.vivek.quicknews.data.model.BaseModel
import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.network.ApiResult
import com.vivek.quicknews.ui.screens.viewModel.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import androidx.compose.foundation.lazy.items

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun MainScreen() {
    val newsViewModel = hiltViewModel<NewsViewModel>()
    LaunchedEffect(key1 = 0) {
        newsViewModel.searchApi("apple")
    }
        newsViewModel.searchData.value?.let {
            if (it is ApiResult.Success<NewsResponse>) {
                LazyColumn {
                    items(it.data.articles) { message ->
                       Text(text = message.title)
                    }
                }
            }
        }
}