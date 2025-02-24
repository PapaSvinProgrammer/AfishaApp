package com.example.afishaapp.data.repository.http

import com.example.afishaapp.data.http.EventService
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.module.event.EventResponse
import com.example.afishaapp.domain.repository.http.EventRepository
import retrofit2.Retrofit
import retrofit2.create
import javax.inject.Inject

class EventRepositoryImpl @Inject constructor(
    private val retrofit: Retrofit
): EventRepository {
    override suspend fun getEvents(currentTime: Int): EventResponse? {
        return try {
            retrofit.create<EventService>().getEvents(currentTime)
        } catch (e: Exception) {
            null
        }
    }

    override suspend fun getEventInfo(eventId: Int): Event? {
        return try {
            retrofit.create<EventService>().getEventInfo(eventId)
        } catch (e: Exception) {
            null
        }
    }
}