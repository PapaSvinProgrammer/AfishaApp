package com.example.afishaapp.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.http.SearchUseCase
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import com.example.afishaapp.domain.room.GetSearchHistory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val preferencesRepository: PreferencesRepository,
    private val searchHistoryRepository: SearchHistoryRepository,
    private val getEvent: GetEvent,
    getSearchHistory: GetSearchHistory
): ViewModel() {
    private var nextPage = 1

    var currentLocation by mutableStateOf("")
        private set
    var events by mutableStateOf<List<Event>>(listOf())
        private set

    var query by mutableStateOf("")
        private set
    var expanded by mutableStateOf(false)
        private set

    var resultItems by mutableStateOf<List<ResultItem>>(listOf())
        private set
    val resultHistory = getSearchHistory.execute().cachedIn(viewModelScope)

    var searchDialogState by mutableStateOf(false)
        private set

    fun getDefaultEvents(location: String) {
        val queryParameters = QueryParameters(
            locationSlug = location
        )

        viewModelScope.launch {
            val response = getEvent.getEvents(queryParameters)

            response?.let {
                events = it.results
            }
        }
    }

    fun search(q: String) {
        clearResultItems()
        nextPage = 1

        viewModelScope.launch(Dispatchers.IO) {
            val result = searchUseCase.search(
                text = q,
                place = currentLocation
            )

            result?.let { resultItems = it.results }
        }
    }

    fun getCurrentPlace() {
        viewModelScope.launch(Dispatchers.IO) {
            preferencesRepository.getLocationSlug().collect {
                currentLocation = it
            }
        }
    }

    fun clearResultItems() {
        resultItems = listOf()
    }

    fun addStringInHistory(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryRepository.insert(query)
        }
    }

    fun loadMoreItems() {
        nextPage++

        viewModelScope.launch(Dispatchers.IO) {
            val result = searchUseCase.search(
                text = query,
                place = currentLocation,
                page = nextPage
            )

            result?.let {
                val temp = resultItems.toMutableList()
                temp.addAll(it.results)
                resultItems = temp
            }
        }
    }

    fun updateQuery(query: String) {
        this.query = query
    }

    fun updateExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

    fun updateSearchDialogState(state: Boolean) {
        searchDialogState = state
    }
}