package com.example.afishaapp.di.data

import com.example.afishaapp.data.auth.LoginFirebaseImpl
import com.example.afishaapp.data.auth.RegistrationFirebaseImpl
import com.example.afishaapp.data.repository.AuthRepositoryFirebase
import com.example.afishaapp.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
interface AuthModuleData {
    @Singleton
    @Binds
    fun bindAuthRepositoryFirebase(repository: AuthRepositoryFirebase): AuthRepository

    companion object {
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