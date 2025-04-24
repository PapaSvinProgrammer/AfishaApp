package com.example.afishaapp.ui.screen.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.room.TicketEntity
import com.example.afishaapp.domain.repository.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {
    var showDetail by mutableStateOf<TicketEntity?>(null)
        private set
    var topBarVisibilityState by mutableStateOf(true)
        private set

    var tickets by mutableStateOf<List<TicketEntity>>(listOf())
        private set

    fun updateShowDetail(state: TicketEntity?) {
        showDetail = state
    }

    fun updateTopBarVisibilityState(state: Boolean) {
        topBarVisibilityState = state
    }

    fun getTicketsByName() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByName()
        }
    }

    fun getTicketsByBuyDate() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByBuyDate()
        }
    }

    fun getTicketsByStartDate() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByStartDate()
        }
    }
}