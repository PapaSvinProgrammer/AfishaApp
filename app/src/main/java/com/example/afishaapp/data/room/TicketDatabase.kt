package com.example.afishaapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [TicketEntity::class], version = 1)
abstract class TicketDatabase: RoomDatabase() {
    abstract fun getTicketDao(): TicketDao
}