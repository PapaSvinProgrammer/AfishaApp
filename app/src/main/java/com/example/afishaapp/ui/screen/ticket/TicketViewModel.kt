package com.example.afishaapp.ui.screen.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.room.ticket.TicketEntity
import com.example.afishaapp.domain.module.TicketFilterType
import com.example.afishaapp.domain.repository.room.TicketRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {
    var topBarVisibilityState by mutableStateOf(true)
        private set
    var filterBottomSheetState by mutableStateOf(false)
        private set
    var currentFilterType by mutableStateOf(TicketFilterType.START_DATE)
        private set

    var tickets by mutableStateOf<List<TicketEntity>>(listOf())
        private set

    fun updateCurrentFilterType(type: TicketFilterType) {
        currentFilterType = type
    }

    fun updateFilterBottomSheetState(state: Boolean) {
        filterBottomSheetState = state
    }

    fun updateTopBarVisibilityState(state: Boolean) {
        topBarVisibilityState = state
    }

    fun getAllTickets() {
        when (currentFilterType) {
            TicketFilterType.NAME -> getTicketsByName()
            TicketFilterType.START_DATE -> getTicketsByStartDate()
            TicketFilterType.BUY_DATE -> getTicketsByBuyDate()
        }
    }

    private fun getTicketsByStartDate() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByStartDate()
        }
    }

    private fun getTicketsByBuyDate() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByBuyDate()
        }
    }

    private fun getTicketsByName() {
        viewModelScope.launch(Dispatchers.IO) {
            tickets = ticketRepository.getAllByName()
        }
    }
}