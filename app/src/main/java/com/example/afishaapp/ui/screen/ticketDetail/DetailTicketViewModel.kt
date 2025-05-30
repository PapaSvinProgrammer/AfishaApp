package com.example.afishaapp.ui.screen.ticketDetail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.room.ticket.TicketEntity
import com.example.afishaapp.domain.repository.room.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailTicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {
    var ticket by mutableStateOf<TicketEntity?>(null)
        private set

    var settingsBottomSheetState by mutableStateOf(false)
        private set
    var deleteDialogState by mutableStateOf(false)
        private set

    fun updateSettingsBottomSheetState(state: Boolean) {
        settingsBottomSheetState = state
    }

    fun updateDeleteDialogState(state: Boolean) {
        deleteDialogState = state
    }

    fun getTicketDetail(eventId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            ticket = ticketRepository.getTicketById(eventId)
        }
    }

    fun deleteTicket() {
        ticket?.let {
            viewModelScope.launch(Dispatchers.IO) {
                ticketRepository.delete(it.eventId)
            }
        }
    }
}