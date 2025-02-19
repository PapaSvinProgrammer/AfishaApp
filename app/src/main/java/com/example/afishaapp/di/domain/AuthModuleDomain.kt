package com.example.afishaapp.di.domain

import com.example.afishaapp.domain.auth.LoginFirebase
import com.example.afishaapp.domain.auth.RegistrationFirebase
import com.example.afishaapp.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides

@Module
interface AuthModuleDomain {
    companion object {
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