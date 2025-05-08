package com.example.afishaapp.data.room.searchHistory

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: SearchHistoryEntity)

    @Query("SELECT `query` FROM search_history ORDER BY id DESC")
    suspend fun getAll(): List<String>
}