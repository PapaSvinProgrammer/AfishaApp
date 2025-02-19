package com.example.afishaapp.domain.preferences

import com.example.afishaapp.domain.repository.PreferencesRepository
import javax.inject.Inject

class SetPreferences @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    suspend fun setEmail(email: String) {
        if (email.isEmpty()) {
            return
        }

        preferencesRepository.setEmail(email)
    }

    suspend fun setUserName(name: String) {
        if (name.isEmpty()) {
            return
        }

        preferencesRepository.setName(name)
    }

    suspend fun setEntryState(state: Boolean) {
        preferencesRepository.setEntryState(state)
    }
}