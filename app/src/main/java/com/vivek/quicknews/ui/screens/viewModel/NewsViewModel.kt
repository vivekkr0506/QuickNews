package com.vivek.quicknews.ui.screens.viewModel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vivek.quicknews.data.model.NewsResponse
import com.vivek.quicknews.data.repository.NewsRepository
import com.vivek.quicknews.network.ApiResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NewsViewModel @Inject constructor(private val repo: NewsRepository) : ViewModel() {
    val searchData: MutableState<ApiResult<NewsResponse>?> = mutableStateOf(null)

    private val _topHeadline = MutableStateFlow<NewsResponse?>(null)
    val topHeadline get() = _topHeadline.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading get() = _isLoading.asStateFlow()
    @ExperimentalCoroutinesApi
    @FlowPreview
    fun searchApi(searchKey: String) {
        viewModelScope.launch {
            flowOf(searchKey).debounce(300).filter {
                it.trim().isEmpty().not()
            }.distinctUntilChanged().flatMapLatest {
                repo.searchNews(it)
            }.collect {
                if (it is ApiResult.Success) {
                    it.data
                }
                Log.e("Vivek", it.toString())
                searchData.value = it
            }
        }
    }

    fun topHeadline() {
        viewModelScope.launch {
            repo.topHeadline().onEach {
                when (it) {
                    is ApiResult.Loading -> {
                        _isLoading.value = true
                    }

                    is ApiResult.Success -> {
                        _topHeadline.value = it.data
                        _isLoading.value = false
                    }

                    is ApiResult.Error -> {
                        _isLoading.value = false
                    }
                }
            }.launchIn(viewModelScope)
        }
    }


}
