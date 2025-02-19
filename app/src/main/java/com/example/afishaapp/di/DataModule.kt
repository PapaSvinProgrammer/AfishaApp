package com.example.afishaapp.di

import android.content.Context
import com.example.afishaapp.data.auth.LoginFirebaseImpl
import com.example.afishaapp.data.auth.RegistrationFirebaseImpl
import com.example.afishaapp.data.repository.AuthRepositoryFirebase
import com.example.afishaapp.data.repository.PreferencesRepositoryDataStore
import com.example.afishaapp.domain.repository.AuthRepository
import com.example.afishaapp.domain.repository.PreferencesRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface DataModule {
    @Singleton
    @Binds
    fun bindAuthRepositoryFirebase(authRepositoryFirebase: AuthRepositoryFirebase): AuthRepository

    companion object {
        @Singleton
        @Provides
        fun providePreferencesRepository(context: Context): PreferencesRepository {
            return PreferencesRepositoryDataStore(context)
        }

        @Singleton
        @Provides
        fun provideLoginFirebaseImpl(): LoginFirebaseImpl {
            return LoginFirebaseImpl()
        }

        @Singleton
        @Provides
        fun provideRegistrationFirebaseImpl(): RegistrationFirebaseImpl {
            return RegistrationFirebaseImpl()
        }
    }
}