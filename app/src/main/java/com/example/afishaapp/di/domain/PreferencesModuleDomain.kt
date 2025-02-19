package com.example.afishaapp.di.domain

import com.example.afishaapp.domain.preferences.GetPreferences
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides

@Module
interface PreferencesModuleDomain {
    companion object {
        @Provides
        fun provideSetPreferences(repository: PreferencesRepository): SetPreferences {
            return SetPreferences(repository)
        }

        @Provides
        fun provideGetPreferences(repository: PreferencesRepository): GetPreferences {
            return GetPreferences(repository)
        }
    }
}