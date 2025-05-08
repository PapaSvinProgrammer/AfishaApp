package com.example.afishaapp.domain.room

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.example.afishaapp.data.room.searchHistory.SearchHistoryEntity
import com.example.afishaapp.domain.repository.room.SearchHistoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetSearchHistory @Inject constructor(
    private val searchHistoryRepository: SearchHistoryRepository
) {
    fun execute(): Flow<PagingData<SearchHistoryEntity>> {
        return Pager(
            config = PagingConfig(
                pageSize = 10,
                prefetchDistance = 20,
            ),
            pagingSourceFactory = { searchHistoryRepository.getAllPaged() }
        ).flow
    }
}