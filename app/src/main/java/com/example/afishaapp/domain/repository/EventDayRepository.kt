package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.module.eventDay.EventDayResponse

interface EventDayRepository {
    suspend fun getEventsDay(): EventDayResponse
}