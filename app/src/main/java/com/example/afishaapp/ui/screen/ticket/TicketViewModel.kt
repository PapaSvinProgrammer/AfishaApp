package com.example.afishaapp.ui.screen.ticket

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.afishaapp.data.room.TicketEntity
import com.example.afishaapp.domain.repository.TicketRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class TicketViewModel @Inject constructor(
    private val ticketRepository: TicketRepository
): ViewModel() {
    var count = 0

    fun add() {
        viewModelScope.launch {
            val ticketEntity = TicketEntity(
                name = "name$count",
                eventId = 1,
                age = "18+",
                address = "asdasd",
                subway = "asdsad, asdas, asd",
                dateStart = 4L,
                dateBuy = 4L,
                duration = 1
            )

            ticketRepository.insert(ticketEntity)
        }
    }
}