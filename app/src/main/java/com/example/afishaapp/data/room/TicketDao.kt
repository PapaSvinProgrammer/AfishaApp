package com.example.afishaapp.data.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface TicketDao {
    @Insert
    suspend fun insert(vararg ticket: TicketEntity)

    @Query("SELECT * FROM Ticket ORDER BY name DESC")
    suspend fun getAllByName(): List<TicketEntity>

    @Query("SELECT * FROM Ticket ORDER BY date_buy DESC")
    suspend fun getAllByDate(): List<TicketEntity>

    @Query("SELECT * FROM Ticket ORDER BY date_buy DESC")
    suspend fun getAllByDateBuy(): List<TicketEntity>

    @Query("DELETE FROM Ticket WHERE event_id = :eventId")
    suspend fun delete(eventId: Int)
}