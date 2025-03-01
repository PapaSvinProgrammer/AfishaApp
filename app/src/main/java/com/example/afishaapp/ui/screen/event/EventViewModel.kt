package com.example.afishaapp.ui.screen.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.domain.http.GetEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val getEvent: GetEvent
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set
    var event by mutableStateOf<Event?>(null)
        private set

    fun updateFavoriteState(state: Boolean) {
        favoriteState = state
    }

    fun getEventInfo(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            event = getEvent.getEventInfo(eventId)
        }
    }
}