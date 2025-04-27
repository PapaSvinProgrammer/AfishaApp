package com.example.afishaapp.domain.repository.room

import com.example.afishaapp.data.room.ticket.TicketEntity

interface TicketRepository {
    suspend fun getAllByName(): List<TicketEntity>
    suspend fun getAllByStartDate(): List<TicketEntity>
    suspend fun getAllByBuyDate(): List<TicketEntity>
    suspend fun getById(eventId: Int): TicketEntity?

    suspend fun search(q: String): List<TicketEntity>
    suspend fun insert(vararg ticket: TicketEntity)
    suspend fun delete(eventId: Int)
}