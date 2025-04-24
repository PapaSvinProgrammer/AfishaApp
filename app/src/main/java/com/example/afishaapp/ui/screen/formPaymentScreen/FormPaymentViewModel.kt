package com.example.afishaapp.ui.screen.formPaymentScreen

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.domain.http.GetEvent
import com.example.afishaapp.domain.room.TicketEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class FormPaymentViewModel @Inject constructor(
    private val getEvent: GetEvent,
    private val ticketEvent: TicketEvent
): ViewModel() {
    var event by mutableStateOf<Event?>(null)
        private set

    var permission by mutableStateOf(false)
        private set
    var isSuccess by mutableStateOf(false)
        private set

    fun updatePermission(state: Boolean) {
        permission = state
    }

    fun updateSuccessState(state: Boolean) {
        isSuccess = state
    }

    fun getEvent(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            event = getEvent.getEventInfo(eventId)
        }
    }

    fun insertTicket() {
        event?.let {
            viewModelScope.launch(Dispatchers.IO) {
                ticketEvent.insert(it)
            }
        }
    }
}