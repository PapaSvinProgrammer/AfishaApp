package com.example.afishaapp.data.repository

import com.example.afishaapp.data.auth.LoginFirebaseImpl
import com.example.afishaapp.data.auth.RegistrationFirebaseImpl
import com.example.afishaapp.data.module.User
import com.example.afishaapp.domain.module.AuthData
import com.example.afishaapp.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryFirebase @Inject constructor(
    private val loginFirebaseImpl: LoginFirebaseImpl,
    private val registrationFirebaseImpl: RegistrationFirebaseImpl
): AuthRepository {
    override suspend fun login(authData: AuthData): User? {
        return loginFirebaseImpl.execute(authData)
    }

    override suspend fun registration(authData: AuthData): User? {
        return registrationFirebaseImpl.execute(authData)
    }
}