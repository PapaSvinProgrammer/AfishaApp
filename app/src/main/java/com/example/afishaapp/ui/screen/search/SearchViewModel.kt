package com.example.afishaapp.ui.screen.search

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.search.ResultItem
import com.example.afishaapp.domain.http.SearchUseCase
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class SearchViewModel @Inject constructor(
    private val searchUseCase: SearchUseCase,
    private val preferencesRepository: PreferencesRepository,
    private val searchHistoryRepository: SearchHistoryRepository
): ViewModel() {
    private var currentLocation by mutableStateOf("")

    var query by mutableStateOf("")
    var expanded by mutableStateOf(false)

    var resultItems by mutableStateOf<List<ResultItem>>(listOf())
        private set
    var resultHistory by mutableStateOf<List<String>>(listOf())
        private set

    fun search(q: String) {
        clearResultItems()

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

    fun getHistory() {
        viewModelScope.launch(Dispatchers.IO) {
            resultHistory = searchHistoryRepository.getAll()
        }
    }

    fun addStringInHistory(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchHistoryRepository.insert(query)
        }
    }
}