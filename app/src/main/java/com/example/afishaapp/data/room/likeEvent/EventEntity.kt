package com.example.afishaapp.data.room.likeEvent

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "like_event",
    indices = [
        Index(
            value = ["event_id"],
            unique = true
        )
    ]
)
data class EventEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "price") val price: String,
    @ColumnInfo(name = "date_start") val dateStart: Long,
    @ColumnInfo(name = "date_end") val dateEnd: Long,
    @ColumnInfo(name = "image") val image: String
)