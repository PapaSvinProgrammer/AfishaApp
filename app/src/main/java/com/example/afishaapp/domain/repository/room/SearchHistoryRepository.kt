package com.example.afishaapp.domain.repository.room

interface SearchHistoryRepository {
    suspend fun insert(query: String)
    suspend fun getAll(): List<String>
}