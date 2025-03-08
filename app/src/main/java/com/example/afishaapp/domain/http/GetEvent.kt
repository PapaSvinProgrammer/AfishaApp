package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.QueryParameters
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.repository.http.EventRepository
import javax.inject.Inject

class GetEvent @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun getEvents(queryParameters: QueryParameters): EventResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        val newQueryParameters = QueryParameters(
            actualSince = currentTime.toInt(),
            locationSlug = queryParameters.locationSlug,
            category = queryParameters.category,
            page = queryParameters.page
        )

        return eventRepository.getEvents(newQueryParameters)
    }

    suspend fun getEventInfo(eventId: Int): Event? {
        if (eventId <= 0) {
            return null
        }

        return eventRepository.getEventInfo(eventId)
    }
}