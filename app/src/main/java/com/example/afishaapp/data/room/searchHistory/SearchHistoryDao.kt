package com.example.afishaapp.data.room.searchHistory

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SearchHistoryDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(item: SearchHistoryEntity)

    @Query("SELECT * FROM search_history ORDER BY id DESC")
    fun getAllPaged(): PagingSource<Int, SearchHistoryEntity>
}