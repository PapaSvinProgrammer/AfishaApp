package com.example.afishaapp.ui.screen.eventList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.repository.http.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val getEvent: GetEvent
): ViewModel() {
    var categories by mutableStateOf<List<Category>>(listOf())
        private set
    var currentCategory by mutableStateOf("")
        private set
    var events by mutableStateOf<EventResponse?>(null)
        private set

    fun updateCurrentCategory(category: String) {
        currentCategory = category
    }

    fun getEvents(location: String, category: String) {
        viewModelScope.launch(Dispatchers.IO) {
            events = getEvent.getEvents(
                location = location,
                category = category
            )
        }
    }

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            categories = categoryRepository.getCategoriesEvent()
        }
    }
}