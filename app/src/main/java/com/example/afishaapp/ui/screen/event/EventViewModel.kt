package com.example.afishaapp.ui.screen.event

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.support.ParseHtml
import com.example.afishaapp.data.module.Coordinate
import com.example.afishaapp.data.module.comment.Comment
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.domain.http.GetCommentEvent
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.repository.http.MapImageRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val getEvent: GetEvent,
    private val getCommentEvent: GetCommentEvent,
    private val mapImageRepository: MapImageRepository
): ViewModel() {
    var favoriteState by mutableStateOf(false)
        private set
    var event by mutableStateOf<Event?>(null)
        private set
    var comments by mutableStateOf<List<Comment>>(listOf())
        private set

    var parseEventDescription by mutableStateOf("")
        private set
    var parseEventBodyText by mutableStateOf("")
        private set

    var imageMap by mutableStateOf("")
        private set

    fun parseEventInfo(
        item: Event? = event
    ) {
        item?.let {
            viewModelScope.launch {
                parseEventDescription = ParseHtml.getText(it.description)
                parseEventBodyText = ParseHtml.getText(it.bodyText)
            }
        }
    }

    fun updateFavoriteState(state: Boolean) {
        favoriteState = state
    }

    fun getEventInfo(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            event = getEvent.getEventInfo(eventId)
        }
    }

    fun getComments(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val temp = getCommentEvent.getDesc(eventId)

            temp?.let {
                comments = it.results
            }
        }
    }

    fun getImageUrl(
        coordinate: Coordinate? = event?.place?.coordinates
    ) {
        if (coordinate == null) {
            return
        }

        viewModelScope.launch(Dispatchers.IO) {
            imageMap = mapImageRepository.getImageUrl(coordinate)
        }
    }
}