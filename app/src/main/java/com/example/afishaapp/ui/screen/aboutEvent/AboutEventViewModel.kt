package com.example.afishaapp.ui.screen.aboutEvent

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.ParseHtml
import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.repository.http.CategoryRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class AboutEventViewModel @Inject constructor(
    private val getEvent: GetEvent,
    private val categoryRepository: CategoryRepository
): ViewModel() {
    private val _categories = HashMap<String, String>()
    val categories: Map<String, String> = _categories

    var event by mutableStateOf<Event?>(null)
        private set
    var parseEventDescription by mutableStateOf("")
        private set
    var parseEventBodyText by mutableStateOf("")
        private set

    fun parseInf(
        event: Event? = this.event
    ) {
        event?.let {
            viewModelScope.launch {
                parseEventDescription = ParseHtml.getText(it.description)
                parseEventBodyText = ParseHtml.getText(it.bodyText)
            }
        }
    }

    fun getEvent(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            event = getEvent.getEventInfo(id)
        }
    }

    fun getCategory() {
        viewModelScope.launch(Dispatchers.IO) {
            val categoriesArray: List<Category> = categoryRepository.getCategoriesEvent()

            categoriesArray.forEach {
                _categories[it.slug] = it.name
            }
        }
    }
}