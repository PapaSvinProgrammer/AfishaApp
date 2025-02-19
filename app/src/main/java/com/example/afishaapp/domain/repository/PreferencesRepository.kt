package com.example.afishaapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setName(text: String)
    suspend fun setEntryState(state: Boolean)
    suspend fun setEmail(text: String)

    fun getName(): Flow<String>
    fun getEntryState(): Flow<Boolean>
    fun getEmail(): Flow<String>
}