package com.example.afishaapp.data.room.likeEvent

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface EventDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(eventEntity: EventEntity)

    @Query("DELETE FROM like_event WHERE event_id = :eventId")
    suspend fun delete(eventId: Int)

    @Query("SELECT * FROM like_event ORDER BY id DESC")
    suspend fun getByDefault(): List<EventEntity>

    @Query("SELECT * FROM like_event ORDER BY name ASC")
    suspend fun getByName(): List<EventEntity>

    @Query("SELECT * FROM like_event WHERE event_id = :eventId")
    suspend fun getEventById(eventId: Int): EventEntity?
}