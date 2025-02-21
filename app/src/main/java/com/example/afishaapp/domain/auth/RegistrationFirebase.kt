package com.example.afishaapp.domain.auth

import com.example.afishaapp.data.module.User
import com.example.afishaapp.domain.module.AuthData
import com.example.afishaapp.domain.repository.AuthRepository
import javax.inject.Inject

class RegistrationFirebase @Inject constructor(
    private val authRepository: AuthRepository
) {
    suspend fun execute(authData: AuthData): User? {
        if (authData.email.isEmpty() || authData.password.isEmpty()) {
            return null
        }

        return authRepository.registration(authData)
    }
}