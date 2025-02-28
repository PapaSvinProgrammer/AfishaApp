package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse

interface EventRepository {
    suspend fun getEvents(
        currentTime: Int,
        location: String,
        category: String,
        page: Int = 1
    ): EventResponse?
    suspend fun getEventInfo(eventId: Int): Event?
}