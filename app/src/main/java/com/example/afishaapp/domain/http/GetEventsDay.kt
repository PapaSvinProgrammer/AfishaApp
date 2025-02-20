package com.example.afishaapp.domain.http

import com.example.afishaapp.data.module.eventDay.EventDayResponse
import com.example.afishaapp.domain.repository.EventDayRepository
import javax.inject.Inject

class GetEventsDay @Inject constructor(
    private val eventDayRepository: EventDayRepository
) {
    suspend fun execute(): EventDayResponse {
        return eventDayRepository.getEventsDay()
    }
}