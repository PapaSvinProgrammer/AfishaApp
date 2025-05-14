package com.example.afishaapp.data.room.ticket

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface TicketDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(vararg ticket: TicketEntity)

    @Query("SELECT * FROM Ticket ORDER BY name ASC")
    suspend fun getAllByName(): List<TicketEntity>

    @Query("SELECT * FROM Ticket ORDER BY date_buy DESC")
    suspend fun getAllByDate(): List<TicketEntity>

    @Query("SELECT * FROM Ticket ORDER BY date_buy DESC")
    suspend fun getAllByDateBuy(): List<TicketEntity>

    @Query("SELECT * FROM Ticket WHERE event_id = :eventId")
    suspend fun getById(eventId: Int): TicketEntity?

    @Query("DELETE FROM Ticket WHERE event_id = :eventId")
    suspend fun delete(eventId: Int)

    @Query("SELECT * FROM Ticket WHERE name LIKE '%' || :q || '%'")
    suspend fun search(q: String): List<TicketEntity>
}