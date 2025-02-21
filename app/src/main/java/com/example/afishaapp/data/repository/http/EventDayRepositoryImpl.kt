package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.EventDayService
import com.example.afishaapp.data.module.eventDay.EventDayResponse
import com.example.afishaapp.domain.repository.http.EventDayRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class EventDayRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): EventDayRepository {
    override suspend fun getEventsDay(): EventDayResponse{
        return retrofit.create<EventDayService>().getEventsDay()
    }
}