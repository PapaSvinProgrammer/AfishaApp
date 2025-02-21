package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.repository.http.EventRepository
import javax.inject.Inject

class GetEvent @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun getEvents(): EventResponse {
        val currentTime = System.currentTimeMillis() / 1000

        return eventRepository.getEvents(currentTime.toInt())
    }

    suspend fun getEventInfo(eventId: Int): Event? {
        if (eventId <= 0) {
            return null
        }

        return eventRepository.getEventInfo(eventId)
    }
}