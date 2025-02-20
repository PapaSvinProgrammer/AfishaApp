package com.example.afishaapp.data.http

import com.example.afishaapp.data.module.eventDay.EventDayResponse
import retrofit2.http.GET

interface EventDayService {
    @GET("public-api/v1.4/events-of-the-day/")
    suspend fun getEventsDay(): EventDayResponse
}