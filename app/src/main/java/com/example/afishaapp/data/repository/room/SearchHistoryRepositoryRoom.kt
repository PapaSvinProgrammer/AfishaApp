package com.example.afishaapp.data.repository.room

import androidx.paging.PagingSource
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

    override suspend fun getCount(): Int {
        return dao.getCount()
    }

    override suspend fun clear() {
        return dao.clear()
    }

    override fun getAllPaged(): PagingSource<Int, SearchHistoryEntity> {
        return dao.getAllPaged()
    }
}