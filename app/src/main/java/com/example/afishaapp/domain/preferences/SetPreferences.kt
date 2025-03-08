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

    suspend fun setDefaultCity(city: String, slug: String) {
        if (city.isEmpty()) {
            return
        }

        setLocationSlug(slug)
        preferencesRepository.setDefaultCity(city)
    }

    suspend fun setAvatarUrl(url: String) {
        if (url.isEmpty()) {
            return
        }

        preferencesRepository.setAvatarUrl(url)
    }

    private suspend fun setLocationSlug(locationSlug: String) {
        if (locationSlug.isEmpty()) {
            return
        }

        preferencesRepository.setLocationSlug(locationSlug)
    }
}