package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.Category
import com.example.afishaapp.data.module.City
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.repository.http.EventRepository
import com.example.afishaapp.ui.screen.bottomSheet.DefaultObject
import javax.inject.Inject

class GetEvent @Inject constructor(
    private val eventRepository: EventRepository
) {
    suspend fun getEvents(
        location: City,
        category: Category = DefaultObject.DEFAULT_CATEGORY
    ): EventResponse? {
        val currentTime = System.currentTimeMillis() / 1000

        return eventRepository.getEvents(
            currentTime = currentTime.toInt(),
            location = location.slug,
            category = category.slug
        )
    }

    suspend fun getEventInfo(eventId: Int): Event? {
        if (eventId <= 0) {
            return null
        }

        return eventRepository.getEventInfo(eventId)
    }
}