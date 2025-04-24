package com.example.afishaapp.domain.room

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.room.TicketEntity
import com.example.afishaapp.domain.repository.TicketRepository
import javax.inject.Inject

class TicketEvent @Inject constructor(
    private val ticketRepository: TicketRepository
) {
    suspend fun insert(event: Event) {
        val currentDate = System.currentTimeMillis()

        val ticketEntity = TicketEntity(
            name = event.shortTitle,
            eventId = event.id,
            age = event.ageRestriction,
            address = event.place?.address ?: "",
            subway = event.place?.subway ?: "",
            dateBuy = currentDate,
            dateStart = event.dates.last().start,
            duration = event.dates.last().end - event.dates.last().start,
            price = event.price,
            location = event.place?.location ?: "",
            image = event.images.first().image,
            phone = event.place?.phone ?: ""
        )

        ticketRepository.insert(ticketEntity)
    }

    suspend fun delete(eventId: Int) {
        if (eventId <= 0) {
            return
        }

        ticketRepository.delete(eventId)
    }
}