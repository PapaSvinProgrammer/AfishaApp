package com.example.afishaapp.di.data

import android.content.Context
import com.example.afishaapp.data.repository.PreferencesRepositoryDataStore
import com.example.afishaapp.domain.repository.PreferencesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface PreferencesModuleData {
    companion object {
        @Singleton
        @Provides
        fun providePreferencesRepository(context: Context): PreferencesRepository {
            return PreferencesRepositoryDataStore(context)
        }
    }
}