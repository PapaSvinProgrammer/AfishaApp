package com.example.afishaapp.domain.repository.http

import com.example.afishaapp.data.module.eventDay.EventDayResponse

interface EventDayRepository {
    suspend fun getEventsDay(): EventDayResponse
}