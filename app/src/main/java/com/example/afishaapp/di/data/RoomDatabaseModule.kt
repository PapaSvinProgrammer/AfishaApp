package com.example.afishaapp.di.data

import android.content.Context
import androidx.appcompat.app.AppCompatDelegate.NightMode
import androidx.room.Room
import com.example.afishaapp.data.repository.room.LikeEventRepositoryRoom
import com.example.afishaapp.data.repository.room.LikeMovieRepositoryRoom
import com.example.afishaapp.data.repository.room.LikePlaceRepositoryRoom
import com.example.afishaapp.data.repository.room.SearchHistoryRepositoryRoom
import com.example.afishaapp.data.repository.room.TicketRepositoryRoom
import com.example.afishaapp.data.room.ticket.TicketDao
import com.example.afishaapp.data.room.AppDatabase
import com.example.afishaapp.data.room.likeEvent.EventDao
import com.example.afishaapp.data.room.likeMovie.MovieDao
import com.example.afishaapp.data.room.likePlace.PlaceDao
import com.example.afishaapp.data.room.searchHistory.SearchHistoryDao
import com.example.afishaapp.domain.repository.room.LikeEventRepository
import com.example.afishaapp.domain.repository.room.LikeMovieRepository
import com.example.afishaapp.domain.repository.room.LikePlaceRepository
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import com.example.afishaapp.domain.repository.room.TicketRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

private const val NAME_DATABASE = "AfishaDatebase"

@Module
interface RoomDatabaseModule {
    companion object {
        @Singleton
        @Provides
        fun provideRoomDatabase(context: Context): AppDatabase {
            val database = Room.databaseBuilder(
                context = context,
                klass = AppDatabase::class.java,
                name = NAME_DATABASE
            ).build()

            return database
        }

        @Singleton
        @Provides
        fun provideTicketDao(database: AppDatabase): TicketDao {
            return database.getTicketDao()
        }

        @Singleton
        @Provides
        fun provideEventDao(database: AppDatabase): EventDao {
            return database.getEventDao()
        }

        @Singleton
        @Provides
        fun provideMovieDao(database: AppDatabase): MovieDao {
            return database.getMovieDao()
        }

        @Singleton
        @Provides
        fun providePlaceDao(database: AppDatabase): PlaceDao {
            return database.getPlaceDao()
        }

        @Singleton
        @Provides
        fun provideSearchHistoryDao(database: AppDatabase): SearchHistoryDao {
            return database.getSearchHistoryDao()
        }
    }

    @Singleton
    @Binds
    fun bindTicketRepository(repository: TicketRepositoryRoom): TicketRepository

    @Singleton
    @Binds
    fun bindLikeEventRepository(repository: LikeEventRepositoryRoom): LikeEventRepository

    @Singleton
    @Binds
    fun bindLikeMovieRepository(repository: LikeMovieRepositoryRoom): LikeMovieRepository

    @Singleton
    @Binds
    fun binLikePlaceRepository(repository: LikePlaceRepositoryRoom): LikePlaceRepository

    @Singleton
    @Binds
    fun bindSearchHistoryRepositoryRoom(repository: SearchHistoryRepositoryRoom): SearchHistoryRepository
}