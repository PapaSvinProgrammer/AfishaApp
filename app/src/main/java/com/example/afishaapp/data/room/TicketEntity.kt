package com.example.afishaapp.data.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Ticket")
data class TicketEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "event_id") val eventId: Int,
    @ColumnInfo(name = "age_restriction") val age: String,
    @ColumnInfo(name = "address") val address: String,
    @ColumnInfo(name = "subway") val subway: String,
    @ColumnInfo(name = "date_buy") val dateBuy: Long,
    @ColumnInfo(name = "date_start") val dateStart: Long,
    @ColumnInfo(name = "duration") val duration: Int
)