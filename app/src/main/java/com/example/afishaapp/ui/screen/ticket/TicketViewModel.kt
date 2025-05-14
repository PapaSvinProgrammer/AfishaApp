package com.example.afishaapp.ui.screen.ticket

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.app.utils.convertClass.toResultItemList
import com.example.afishaapp.data.module.search.ResultItem
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
    var searchResult by mutableStateOf<List<ResultItem>>(listOf())
        private set

    var query by mutableStateOf("")
        private set
    var expanded by mutableStateOf(false)
        private set

    fun updateQuery(query: String) {
        this.query = query
    }

    fun updateExpanded(expanded: Boolean) {
        this.expanded = expanded
    }

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

    fun search(q: String) {
        if (q.length < 3) return

        viewModelScope.launch(Dispatchers.IO) {
            searchResult = ticketRepository.search(q).toResultItemList()
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