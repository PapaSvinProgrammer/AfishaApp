package com.example.afishaapp.data.room.searchHistory

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: SearchHistoryEntity)

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAllPaged(): PagingSource<Int, SearchHistoryEntity>

    @Query("SELECT COUNT(*) FROM search_history")
    fun getCount(): Flow<Int>

    @Query("DELETE FROM search_history")
    suspend fun clear()
}