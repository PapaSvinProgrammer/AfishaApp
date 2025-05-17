package com.example.afishaapp.ui.screen.chart

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.afishaapp.app.utils.convertClass.toCategoryMap
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.domain.repository.room.LikeEventRepository
import com.example.afishaapp.domain.repository.room.LikeMovieRepository
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import com.example.afishaapp.domain.room.GetSearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChartViewModel @Inject constructor(
    getSearchHistory: GetSearchHistory,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val likeEventRepository: LikeEventRepository,
    private val likeMovieRepository: LikeMovieRepository,
    private val likePlaceRepository: LikePlaceRepository
): ViewModel() {
    private val _countFavorite = mutableStateMapOf<String, Int>()
    private val _eventCategory = mutableStateMapOf<String, Int>()

    val countFavorite: Map<String, Int> = _countFavorite
    val eventCategory: Map<String, Int> = _eventCategory

    var searchHistoryCount by mutableIntStateOf(0)
        private set
    var categories by mutableStateOf<List<Category>>(listOf())
        private set

    var clearHistoryDialogState by mutableStateOf(false)
        private set
    var historyResultDialogState by mutableStateOf(false)
        private set

    val searchHistoryResult = getSearchHistory.execute().cachedIn(viewModelScope)

    fun updateClearDialogState(state: Boolean) {
        clearHistoryDialogState = state
    }

    fun updateResultDialogState(state: Boolean) {
        historyResultDialogState = state
    }

    fun getAllCountFavorite() {
        viewModelScope.launch(Dispatchers.IO) {
            likeEventRepository.getCount().collect {
                _countFavorite["События"] = it
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            likeMovieRepository.getCount().collect {
                _countFavorite["Фильмы"] = it
            }
        }

        viewModelScope.launch(Dispatchers.IO) {
            likePlaceRepository.getCount().collect {
                _countFavorite["Места"] = it
            }
        }
    }

    fun getCountEventCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val map = likeEventRepository
                .getCategoryCount()
                .toCategoryMap()

            _eventCategory.putAll(map)
        }
    }

    fun getCountSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryRepository.getCount().collect {
                searchHistoryCount = it
            }
        }
    }

    fun clearSearchHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryRepository.clear()
        }
    }
}