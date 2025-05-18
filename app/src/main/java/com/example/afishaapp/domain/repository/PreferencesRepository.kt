package com.example.afishaapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setEntryState(state: Boolean)
    suspend fun setDefaultCity(text: String)
    suspend fun setLocationSlug(text: String)
    suspend fun setDarkTheme(state: Boolean)

    fun getEntryState(): Flow<Boolean>
    fun getDefaultCity(): Flow<String>
    fun getLocationSlug(): Flow<String>
    fun getDarkTheme(): Flow<Boolean>
}