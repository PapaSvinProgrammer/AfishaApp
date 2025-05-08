package com.example.afishaapp.data.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.afishaapp.data.room.likeEvent.EventEntity
import com.example.afishaapp.data.room.likeEvent.EventDao
import com.example.afishaapp.data.room.likeMovie.MovieDao
import com.example.afishaapp.data.room.likeMovie.MovieEntity
import com.example.afishaapp.data.room.likePlace.PlaceDao
import com.example.afishaapp.data.room.likePlace.PlaceEntity
import com.example.afishaapp.data.room.searchHistory.SearchHistoryDao
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity
import com.example.afishaapp.data.room.ticket.TicketDao
import com.example.afishaapp.data.room.ticket.TicketEntity

@Database(
    entities = [
        TicketEntity::class,
        EventEntity::class,
        MovieEntity::class,
        PlaceEntity::class,
        SearchHistoryEntity::class
    ],
    version = 1
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun getTicketDao(): TicketDao
    abstract fun getEventDao(): EventDao
    abstract fun getMovieDao(): MovieDao
    abstract fun getPlaceDao(): PlaceDao
    abstract fun getSearchHistoryDao(): SearchHistoryDao
}