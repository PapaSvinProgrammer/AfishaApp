package com.example.afishaapp.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    fun setName(text: String)
    fun setEntryState(state: Boolean)
    fun setEmail(text: String)

    fun getName(): Flow<String>
    fun getEntryState(): Flow<String>
    fun getEmail(): Flow<String>
}