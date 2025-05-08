package com.example.afishaapp.data.repository.room

import com.example.afishaapp.data.room.searchHistory.SearchHistoryDao
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import javax.inject.Inject

class SearchHistoryRepositoryRoom @Inject constructor(
    private val dao: SearchHistoryDao
): SearchHistoryRepository {
    override suspend fun insert(query: String) {
        val item = SearchHistoryEntity(query = query)
        dao.insert(item)
    }

    override suspend fun getAll(): List<String> {
        return dao.getAll()
    }
}