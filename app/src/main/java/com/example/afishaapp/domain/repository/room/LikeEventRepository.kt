package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.room.likeEvent.EventCategoryCount
import com.example.afishaapp.data.room.likeEvent.EventEntity
import kotlinx.coroutines.flow.Flow

interface LikeEventRepository {
    suspend fun insert(event: Event)
    suspend fun delete(eventId: Int)
    suspend fun getByDefault(): List<EventEntity>
    suspend fun getByName(): List<EventEntity>
    suspend fun getEventById(eventId: Int): EventEntity?
    fun getCount(): Flow<Int>
    suspend fun getCategoryCount(): List<EventCategoryCount>
}