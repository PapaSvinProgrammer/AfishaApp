package com.example.afishaapp.domain.repository.room

import androidx.paging.PagingSource
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity
import kotlinx.coroutines.flow.Flow

interface SearchHistoryRepository {
    suspend fun insert(query: String)
    suspend fun clear()
    fun getCount(): Flow<Int>
    fun getAllPaged(): PagingSource<Int, SearchHistoryEntity>
}