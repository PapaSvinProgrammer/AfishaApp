package com.example.afishaapp.domain.repository.room

import androidx.paging.PagingSource
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity

interface SearchHistoryRepository {
    suspend fun insert(query: String)
    fun getAllPaged(): PagingSource<Int, SearchHistoryEntity>
}