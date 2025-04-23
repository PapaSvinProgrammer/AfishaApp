package com.example.afishaapp.domain.repository

import com.example.afishaapp.data.room.TicketEntity

interface TicketRepository {
    suspend fun getAllByName(): List<TicketEntity>
    suspend fun getAllByStartDate(): List<TicketEntity>
    suspend fun getAllByBuyDate(): List<TicketEntity>

    suspend fun insert(vararg ticket: TicketEntity)
    suspend fun delete(eventId: Int)
}