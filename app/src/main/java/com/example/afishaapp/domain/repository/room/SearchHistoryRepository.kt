package com.example.afishaapp.domain.repository.room

import androidx.paging.PagingSource
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity

interface SearchHistoryRepository {
    suspend fun insert(query: String)
    suspend fun getCount(): Int
    suspend fun clear()
    fun getAllPaged(): PagingSource<Int, SearchHistoryEntity>
}