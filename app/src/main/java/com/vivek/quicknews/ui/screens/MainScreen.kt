package com.vivek.quicknews.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.network.ApiResult
import com.vivek.quicknews.ui.screens.viewModel.NewsViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview

@OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
@Composable
fun MainScreen(searchKey : String) {
    val newsViewModel = hiltViewModel<NewsViewModel>()
    LaunchedEffect(key1 = 0) {
        newsViewModel.searchApi("apple")
    }
    newsViewModel.searchData.value?.let {
        if (it is ApiResult.Success<NewsResponse>) {
            LazyColumn {
                items(it.data.articles) { article ->
                    if (article.urlToImage.isNullOrEmpty().not()) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(250.dp)
                                .padding(all = 8.dp)
                                .clip(RoundedCornerShape(12.dp))
                        ) {
                            Image(
                                painter = rememberAsyncImagePainter(article.urlToImage ?: ""),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.fillMaxSize()
                            )
                            Box(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .background(
                                        brush = Brush.verticalGradient(
                                            colors = listOf(Color.Transparent, Color.Black),
                                            startY = Float.POSITIVE_INFINITY,
                                            endY = 0f
                                        )
                                    )
                            )
                            Text(
                                text = article.title ?: "",
                                modifier = Modifier.padding(top = 40.dp, start = 12.dp, end = 12.dp, bottom = 8.dp),
                                style = TextStyle(
                                    color = Color.White,
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp
                                )
                            )

                            Row(
                                modifier = Modifier.align(Alignment.TopStart).padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    style = TextStyle(
                                        color = Color.Blue,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    ), text = "Bookmark this"
                                )
                                Icon(
                                    imageVector = Icons.Filled.Star,
                                    contentDescription = "",
                                    tint = Color.Blue
                                )
                            }

                            Row(
                                modifier = Modifier.align(Alignment.BottomEnd).padding(12.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    style = TextStyle(
                                        color = Color.Red,
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 16.sp
                                    ), text = "Read More"
                                )
                                Icon(
                                    imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                                    contentDescription = "",
                                    tint = Color.Red
                                )

                            }
                        }
                    }
                }
            }
        }
    }
}