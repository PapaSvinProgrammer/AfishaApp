package com.example.afishaapp.data.repository.room

import com.example.afishaapp.app.utils.convertClass.toTicketEntity
import com.example.afishaapp.data.module.event.Event
import com.example.afishaapp.data.room.ticket.TicketDao
import com.example.afishaapp.data.room.ticket.TicketEntity
import com.example.afishaapp.domain.repository.room.TicketRepository
import javax.inject.Inject

class TicketRepositoryRoom @Inject constructor(private val dao: TicketDao): TicketRepository {
    override suspend fun getAllByName(): List<TicketEntity> {
        return dao.getAllByName()
    }

    override suspend fun getAllByStartDate(): List<TicketEntity> {
        return dao.getAllByDate()
    }

    override suspend fun getAllByBuyDate(): List<TicketEntity> {
        return dao.getAllByDateBuy()
    }

    override suspend fun getTicketById(eventId: Int): TicketEntity? {
        return dao.getById(eventId)
    }

    override suspend fun search(q: String): List<TicketEntity> {
        return dao.search(q)
    }

    override suspend fun insert(event: Event) {
        dao.insert(event.toTicketEntity())
    }

    override suspend fun delete(eventId: Int) {
        dao.delete(eventId)
    }
}