package com.example.afishaapp.ui.screen.eventList

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.repository.http.CategoryRepository
import com.example.afishaapp.ui.screen.bottomSheet.DefaultObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventListViewModel @Inject constructor(
    private val categoryRepository: CategoryRepository,
    private val getEvent: GetEvent
): ViewModel() {
    var isStartCategories = true
        private set
    var categories by mutableStateOf<List<Category>>(listOf())
        private set
    var currentCategory by mutableStateOf(DefaultObject.DEFAULT_CATEGORY)
        private set
    var events by mutableStateOf<EventResponse?>(null)
        private set
    var categoryStateBottomSheet by mutableStateOf(false)
        private set

    fun updateCurrentCategory(category: Category) {
        currentCategory = category
    }

    fun getEvents(location: String, category: Category) {
        viewModelScope.launch(Dispatchers.IO) {
            events = getEvent.getEvents(
                location = location,
                category = category.slug
            )
        }
    }

    fun getCategories() {
        if (categories.isNotEmpty()) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            val temp = arrayListOf(DefaultObject.DEFAULT_CATEGORY)
            temp.addAll(categoryRepository.getCategoriesEvent())

            categories = temp
        }
    }

    fun updateCategoryStateBottomSheet(state: Boolean) {
        categoryStateBottomSheet = state
    }

    fun startCategorySuccess() {
        isStartCategories = false
    }
}