package com.example.afishaapp.di

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
    }
}