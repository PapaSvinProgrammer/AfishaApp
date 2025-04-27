package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.room.likeEvent.EventEntity

interface LikeEventRepository {
    suspend fun insert(event: Event)
    suspend fun delete(eventId: Int)
    suspend fun getByDefault(): List<EventEntity>
    suspend fun getByName(): List<EventEntity>
    suspend fun getEventById(eventId: Int): EventEntity?
    suspend fun search(q: String): List<EventEntity>
}