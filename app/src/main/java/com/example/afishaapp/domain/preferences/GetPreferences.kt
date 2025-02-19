package com.example.afishaapp.domain.preferences

import com.example.afishaapp.domain.repository.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetPreferences @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    fun getEmail(): Flow<String> {
        return preferencesRepository.getEmail()
    }

    fun getUserName(): Flow<String> {
        return preferencesRepository.getName()
    }

    fun getEntryState(): Flow<Boolean> {
        return preferencesRepository.getEntryState()
    }
}