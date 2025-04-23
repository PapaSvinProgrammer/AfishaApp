package com.example.afishaapp.di.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.afishaapp.data.repository.TicketRepositoryRoom
import com.example.afishaapp.data.room.TicketDao
import com.example.afishaapp.data.room.TicketDatabase
import com.example.afishaapp.domain.repository.TicketRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val NAME_DATABASE = "TicketDatabase"

@Module
interface RoomDatabaseModule {
    companion object {
        @Singleton
        @Provides
        fun provideRoomDatabase(context: Context): TicketDatabase {
            val database = Room.databaseBuilder(
                context = context,
                klass = TicketDatabase::class.java,
                name = NAME_DATABASE
            ).build()

            return database
        }

        @Singleton
        @Provides
        fun provideTicketDao(database: TicketDatabase): TicketDao {
            return database.getTicketDao()
        }
    }

    @Singleton
    @Binds
    fun bindTicketRepository(repository: TicketRepositoryRoom): TicketRepository
}