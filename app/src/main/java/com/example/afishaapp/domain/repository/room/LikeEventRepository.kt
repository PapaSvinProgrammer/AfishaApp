package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.room.likeEvent.EventEntity

interface LikeEventRepository {
    suspend fun insert(eventEntity: EventEntity)
    suspend fun delete(eventId: Int)
    suspend fun getByDefault(): List<EventEntity>
    suspend fun getByName(): List<EventEntity>
    suspend fun search(q: String): List<EventEntity>
}