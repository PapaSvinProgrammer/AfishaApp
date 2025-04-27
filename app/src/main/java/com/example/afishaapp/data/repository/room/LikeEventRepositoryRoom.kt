package com.example.afishaapp.data.repository.room

import com.example.afishaapp.app.utils.toEventEntity
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.room.likeEvent.EventDao
import com.example.afishaapp.data.room.likeEvent.EventEntity
import com.example.afishaapp.domain.repository.room.LikeEventRepository
import javax.inject.Inject

class LikeEventRepositoryRoom @Inject constructor(
    private val dao: EventDao
): LikeEventRepository {
    override suspend fun insert(event: Event) {
        dao.insert(event.toEventEntity())
    }

    override suspend fun delete(eventId: Int) {
        dao.delete(eventId)
    }

    override suspend fun getByDefault(): List<EventEntity> {
        return dao.getByDefault()
    }

    override suspend fun getByName(): List<EventEntity> {
        return dao.getByName()
    }

    override suspend fun getEventById(eventId: Int): EventEntity? {
        return dao.getEventById(eventId)
    }

    override suspend fun search(q: String): List<EventEntity> {
        TODO("Not yet implemented")
    }
}