package com.example.afishaapp.di

import com.example.afishaapp.domain.auth.LoginFirebase
import com.example.afishaapp.domain.auth.RegistrationFirebase
import com.example.afishaapp.domain.preferences.GetPreferences
import com.example.afishaapp.domain.preferences.SetPreferences
import com.example.afishaapp.domain.repository.AuthRepository
import com.example.afishaapp.domain.repository.PreferencesRepository
import com.example.afishaapp.domain.valid.ValidEmail
import com.example.afishaapp.domain.valid.ValidPassword
import dagger.Module
import dagger.Provides

@Module
interface DomainModule {
    companion object {
        @Provides
        fun provideValidEmail(): ValidEmail {
            return ValidEmail()
        }

        @Provides
        fun provideValidPassword(): ValidPassword {
            return ValidPassword()
        }

        @Provides
        fun provideSetPreferences(repository: PreferencesRepository): SetPreferences {
            return SetPreferences(repository)
        }

        @Provides
        fun provideGetPreferences(repository: PreferencesRepository): GetPreferences {
            return GetPreferences(repository)
        }

        @Provides
        fun provideRegistrationFirebase(repository: AuthRepository): RegistrationFirebase {
            return RegistrationFirebase(repository)
        }

        @Provides
        fun provideLoginFirebase(repository: AuthRepository): LoginFirebase {
            return LoginFirebase(repository)
        }
    }
}