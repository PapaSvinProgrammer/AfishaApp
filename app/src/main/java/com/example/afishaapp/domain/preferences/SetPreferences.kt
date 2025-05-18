package com.example.afishaapp.domain.preferences

import com.example.afishaapp.domain.repository.PreferencesRepository
import javax.inject.Inject

class SetPreferences @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) {
    suspend fun setDarkTheme(state: Boolean) {
        preferencesRepository.setDarkTheme(state)
    }

    suspend fun setEntryState(state: Boolean) {
        preferencesRepository.setEntryState(state)
    }

    suspend fun setDefaultCity(city: String, slug: String) {
        if (city.isEmpty()) {
            return
        }

        setLocationSlug(slug)
        preferencesRepository.setDefaultCity(city)
    }

    private suspend fun setLocationSlug(locationSlug: String) {
        if (locationSlug.isEmpty()) {
            return
        }

        preferencesRepository.setLocationSlug(locationSlug)
    }
}