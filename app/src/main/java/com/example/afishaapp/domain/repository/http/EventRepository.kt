package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse

interface EventRepository {
    suspend fun getEvents(queryParameters: QueryParameters): EventResponse?
    suspend fun getEventInfo(eventId: Int): Event?
}